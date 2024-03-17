package org.example.technologie_sieciowe_1.service;

import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Iterable<LoanEntity> getAll() {
        return loanRepository.findAll();
    }


    public LoanEntity getById(Integer id){
        return loanRepository.findById(id).orElse(null);
    }

    public LoanEntity add(LoanEntity loan) {
        return loanRepository.save(loan);
    }

    public void delete(Integer id) {
        loanRepository.deleteById(id);
    }


}
