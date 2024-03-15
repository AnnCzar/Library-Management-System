package org.example.technologie_sieciowe_1.service;

import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.RentalEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public Iterable<RentalEntity> getAll() {
        return rentalRepository.findAll();
    }


    public RentalEntity getById(Integer id){
        return rentalRepository.findById(id).orElse(null);
    }

    public RentalEntity add(RentalEntity rental) {
        return rentalRepository.save(rental);
    }

    public void delete(Long id) {
        rentalRepository.deleteById(Math.toIntExact(id));
    }


}
