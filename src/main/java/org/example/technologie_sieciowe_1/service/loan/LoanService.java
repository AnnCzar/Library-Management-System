package org.example.technologie_sieciowe_1.service.loan;

import org.example.technologie_sieciowe_1.controllers.dto.create.CreateLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateLoanResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.LoanRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;

import org.example.technologie_sieciowe_1.service.book.exceptions.BookNotFoundException;
import org.example.technologie_sieciowe_1.service.auth_user.exceptions.UserNotFoundException;
import org.example.technologie_sieciowe_1.service.loan.exceptions.LoanNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<GetLoanDto> getAll() {
        var loans = loanRepository.findAll();
        return StreamSupport.stream(loans.spliterator(), false).map(this::mapLoan).collect(Collectors.toList());
    }

    public GetLoanDto getById(Integer id){
        var loanEntity =  loanRepository.findById(id).orElseThrow(() -> LoanNotFoundException.create(id));
        return mapLoan(loanEntity);
    }

    public CreateLoanResponseDto add(CreateLoanDto loan) {

        UserEntity user = userRepository.findById(loan.getUser()).orElseThrow(() -> UserNotFoundException.create(loan.getUser()));
        BookEntity book = bookRepository.findById(loan.getBook()).orElseThrow(BookNotFoundException::create);
        var loanEntity = new LoanEntity();
        loanEntity.setLoanDate(loan.getLoanDate());
        loanEntity.setLoanEndDate(loan.getLoanEndDate());
        loanEntity.setBook(book);
        loanEntity.setUser(user);
        loanEntity.setReturnDate(loan.getReturnDate());

        var newLoan = loanRepository.save(loanEntity);
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
