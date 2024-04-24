package org.example.technologie_sieciowe_1.service.loan;

import io.swagger.v3.oas.annotations.Operation;
import org.example.technologie_sieciowe_1.commonTypes.UserRole;
import org.example.technologie_sieciowe_1.controllers.dto.create.CreateLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetLoansPageDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Operation(summary = "Get all loans")
    public GetLoansPageDto getAll(String username, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        var auth = authRepository.findByUsername(username).orElseThrow(() -> UserNotFoundException.create(username));
        var role = auth.getRole();
        var userId = auth.getId();
        Page<LoanEntity> loans;
        if (role == UserRole.ROLE_LIBRARIAN) {
            loans = loanRepository.findAll(pageable);
        } else {
            loans = loanRepository.findByUserId(userId, pageable);
        }
        List<GetLoanDto> loansDto = loans.getContent().stream().map(this::mapLoan).toList();
        return new GetLoansPageDto(
                loansDto, loans.getNumber(),
                loans.getTotalElements(),
                loans.getTotalPages(),
                loans.hasNext());
    }
    @Operation(summary = "Get loan by ID")
    public GetLoanDto getById( Integer id, String username){
        
        var auth = authRepository.findByUsername(username).orElseThrow(() -> UserNotFoundException.create(username));
        var role = auth.getRole();

        LoanEntity loanEntity = null;
        if (role == UserRole.ROLE_LIBRARIAN) {
            loanEntity =  loanRepository.findById(id).orElseThrow(() -> LoanNotFoundException.create(id));
            return mapLoan(loanEntity);
        }else {
            List<LoanEntity> loans = loanRepository.findByUserId(id);
            List<Integer> loanIds = new ArrayList<>();

            for (LoanEntity loan : loans) {  // to get user loanids
                loanIds.add(loan.getLoanId());
            }
            if (loanIds.contains(id)){          // to check if user has te enter  loan id
                return mapLoan(loanEntity);
            }else{

                throw LoanNotFoundException.create(id);
            }
        }
    }
    @Operation(summary = "Add a loan")
    public CreateLoanResponseDto add(CreateLoanDto loan) {


        UserEntity user = userRepository.findById(loan.getUser()).orElseThrow(() -> UserNotFoundException.create(loan.getUser()));
        BookEntity book = bookRepository.findById(loan.getBook()).orElseThrow(BookNotFoundException::create);


        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setLoanDate(new Date());
        loanEntity.setLoanEndDate(loan.getLoanEndDate());
        loanEntity.setBook(book);
        loanEntity.setUser(user);
        loanEntity.setReturnDate(loan.getReturnDate());

        LoanEntity newLoan = loanRepository.save(loanEntity);

        decreaseBookCount(book);
        bookRepository.save(book);

        return new CreateLoanResponseDto(newLoan.getLoanId(),
                newLoan.getBook().getId(),
                newLoan.getUser().getId(),
                newLoan.getLoanDate(),
                newLoan.getLoanEndDate(),
                newLoan.getReturnDate());
    }
    @Operation(summary = "Delete a loan")
    public void delete(Integer id) {
        if (!loanRepository.existsById(id)){
            throw LoanNotFoundException.create(id);
        }
        loanRepository.deleteById(id);
    }
    @Operation(summary = "Update return date for a loan")
    public void updateReturnDate(Integer loanId) {

        var loanEntity = loanRepository.findById(loanId)
                .orElseThrow(() -> LoanNotFoundException.create(loanId));
        loanEntity.setReturnDate(new Date());

        loanRepository.save(loanEntity);
        updateBookQuantity(loanEntity.getBook().getId());
    }
    @Operation(summary = "Update books' quantity")
    private void updateBookQuantity(Integer bookId) {
        var bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> BookNotFoundException.create());

        bookEntity.setNumberCopy(bookEntity.getNumberCopy() + 1);
        bookRepository.save(bookEntity);
    }
    @Operation(summary = "Decrease books' count")
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

        return new GetLoanDto(loanEntity.getLoanId(),
                getBookDto,
                getUserDto,
                loanEntity.getLoanDate(),
                loanEntity.getLoanEndDate(),
                loanEntity.getReturnDate());
    }
}
