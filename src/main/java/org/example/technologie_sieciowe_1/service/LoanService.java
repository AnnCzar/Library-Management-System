package org.example.technologie_sieciowe_1.service;

import org.example.technologie_sieciowe_1.controllers.dto.create.CreateLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateLoanResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<GetLoanDto> getAll() {
        var loans = loanRepository.findAll();

        return StreamSupport.stream(loans.spliterator(), false)
                .map(loan -> new GetLoanDto(loan.getLoanid(),
                        loan.getBook(),
                        loan.getUser(),
                        loan.getLoanDate(),
                        loan.getLoanEndDate(),
                        loan.getReturnDate()))
                .collect(Collectors.toList());
    }


    public GetLoanDto getById(Integer id){
        var loanEntity =  loanRepository.findById(id).orElse(null);

        assert loanEntity != null;
        return new GetLoanDto(loanEntity.getLoanid(),
                loanEntity.getBook(),
                loanEntity.getUser(),
                loanEntity.getLoanDate(),
                loanEntity.getLoanEndDate(),
                loanEntity.getReturnDate());
    }

    public CreateLoanResponseDto add(CreateLoanDto loan) {
        var loanEntity = new LoanEntity();
        loanEntity.setLoanDate(loan.getLoanDate());
        loanEntity.setLoanEndDate(loan.getLoanEndDate());
        loanEntity.setBook(loan.getBook());
        loanEntity.setUser(loan.getUser());
        loanEntity.setReturnDate(loan.getReturnDate());

        var newLoan = loanRepository.save(loanEntity);
        return new CreateLoanResponseDto(newLoan.getLoanid(),
                newLoan.getBook(),
                newLoan.getUser(),
                newLoan.getLoanDate(),
                newLoan.getLoanEndDate(),
                newLoan.getReturnDate());


    }

    public void delete(Integer id) {
        if (!loanRepository.existsById(id)){
            throw new RuntimeException();
        }
        loanRepository.deleteById(id);
    }


}
