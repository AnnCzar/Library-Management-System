package org.example.technologie_sieciowe_1.service.loan;

import org.example.technologie_sieciowe_1.commonTypes.UserRole;
import org.example.technologie_sieciowe_1.controllers.dto.create.CreateLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateLoanResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.AuthRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.LoanRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;

import org.example.technologie_sieciowe_1.service.book.exceptions.BookNotFoundException;
import org.example.technologie_sieciowe_1.service.auth_user.exceptions.UserNotFoundException;
import org.example.technologie_sieciowe_1.service.loan.exceptions.InsufficientBooksAmount;
import org.example.technologie_sieciowe_1.service.loan.exceptions.LoanNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository, AuthRepository authRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.authRepository = authRepository;
    }

    public List<GetLoanDto> getAll(String username) {
        List<LoanEntity> loans;
        var auth = authRepository.findByUserName(username).orElseThrow(() -> UserNotFoundException.create(username));
        var role = auth.getRole();
        var userId = auth.getId(); 
        if (role == UserRole.ROLE_LIBRARIAN) {
            loans = (List<LoanEntity>) loanRepository.findAll();
        } else {
            loans = loanRepository.findByUserId(userId);
        }
        return StreamSupport.stream(loans.spliterator(), false).map(this::mapLoan).collect(Collectors.toList());
    }

    public GetLoanDto getById( Integer id, String username){
        
        var auth = authRepository.findByUserName(username).orElseThrow(() -> UserNotFoundException.create(username));
        var role = auth.getRole();

        LoanEntity loanEntity = null;
        if (role == UserRole.ROLE_LIBRARIAN) {
            loanEntity =  loanRepository.findById(id).orElseThrow(() -> LoanNotFoundException.create(id));
            return mapLoan(loanEntity);
        }else {
            List<LoanEntity> loans = loanRepository.findByUserId(id);
            List<Integer> loanIds = new ArrayList<>();

            for (LoanEntity loan : loans) {  // to get user loanids
                loanIds.add(loan.getLoanid());
            }
            if (loanIds.contains(id)){          // to check if user has te enter  loan id
                return mapLoan(loanEntity);
            }else{

                throw LoanNotFoundException.create(id);
            }
        }
    }

    public CreateLoanResponseDto add(CreateLoanDto loan, String username) {

        var auth = authRepository.findByUserName(username)
                .orElseThrow(() -> UserNotFoundException.create(username));

        if (auth.getRole() != UserRole.ROLE_LIBRARIAN && !Objects.equals(auth.getId(), loan.getUser())) {
            throw new UserNotFoundException("User not found or unauthorized to perform this action.");
        }
        UserEntity user = userRepository.findById(loan.getUser()).orElseThrow(() -> UserNotFoundException.create(loan.getUser()));
        BookEntity book = bookRepository.findById(loan.getBook()).orElseThrow(BookNotFoundException::create);



        // creating new loan
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setLoanDate(new Date());
        loanEntity.setLoanEndDate(loan.getLoanEndDate());
        loanEntity.setBook(book);
        loanEntity.setUser(user);
        loanEntity.setReturnDate(loan.getReturnDate());

        // saving in the database
        LoanEntity newLoan = loanRepository.save(loanEntity);

        decreaseBookCount(book);
        bookRepository.save(book);

        return new CreateLoanResponseDto(newLoan.getLoanid(),
                newLoan.getBook().getId(),
                newLoan.getUser().getId(),
                newLoan.getLoanDate(),
                newLoan.getLoanEndDate(),
                newLoan.getReturnDate());
    }

    public void delete(Integer id) {
        if (!loanRepository.existsById(id)){
            throw LoanNotFoundException.create(id);
        }
        loanRepository.deleteById(id);
    }

    public void updateReturnDate(Integer loanId, String username) {

        var loanEntity = loanRepository.findById(loanId)
                .orElseThrow(() -> LoanNotFoundException.create(loanId));

        var auth = authRepository.findByUserName(username)
                .orElseThrow(() -> UserNotFoundException.create(username));


        if (auth.getRole() == UserRole.ROLE_LIBRARIAN || Objects.equals(loanEntity.getUser().getId(), auth.getId())) {
            loanEntity.setReturnDate(new Date());

            loanRepository.save(loanEntity);
            updateBookQuantity(loanEntity.getBook().getId());
        } else {
            throw LoanNotFoundException.create(loanId);
        }
    }

    private void updateBookQuantity(Integer bookId) {
        var bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> BookNotFoundException.create());

        bookEntity.setNumberCopy(bookEntity.getNumberCopy() + 1);
        bookRepository.save(bookEntity);
    }
    private void decreaseBookCount(BookEntity book) {
        if (book.getNumberCopy() <= 0) {
            throw InsufficientBooksAmount.create();
        }
        book.setNumberCopy(book.getNumberCopy() - 1);
        bookRepository.save(book);
    }

    public GetLoanDto mapLoan(LoanEntity loanEntity){

        GetUserDto getUserDto = new GetUserDto(
                loanEntity.getUser().getId(),
                loanEntity.getUser().getUserName(),
                loanEntity.getUser().getEmail(),
                loanEntity.getUser().getFullUserName());

        GetBookDto getBookDto = new GetBookDto(
                loanEntity.getBook().getId(),
                loanEntity.getBook().getIsbn(),
                loanEntity.getBook().getTitle(),
                loanEntity.getBook().getAuthor(),
                loanEntity.getBook().getPublisher(),
                loanEntity.getBook().getPublishYear(),
                loanEntity.getBook().getNumberCopy());

        return new GetLoanDto(loanEntity.getLoanid(),
                getBookDto,
                getUserDto,
                loanEntity.getLoanDate(),
                loanEntity.getLoanEndDate(),
                loanEntity.getReturnDate());
    }

}


// ewentulana poprawa
//private LoanEntity saveLoan(CreateLoanDto loan, UserEntity user, BookEntity book) {
//    LoanEntity loanEntity = new LoanEntity();
//    loanEntity.setLoanDate(new Date());
//    loanEntity.setLoanEndDate(loan.getLoanEndDate());
//    loanEntity.setBook(book);
//    loanEntity.setUser(user);
//    loanEntity.setReturnDate(loan.getReturnDate());
//    return loanRepository.save(loanEntity);
//}
//
//private CreateLoanResponseDto createLoanResponse(LoanEntity newLoan) {
//    return new CreateLoanResponseDto(
//            newLoan.getLoanid(),
//            newLoan.getBook().getId(),
//            newLoan.getUser().getId(),
//            newLoan.getLoanDate(),
//            newLoan.getLoanEndDate(),
//            newLoan.getReturnDate());
//}