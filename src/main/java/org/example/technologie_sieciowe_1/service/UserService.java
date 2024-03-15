package org.example.technologie_sieciowe_1.service;

import org.apache.catalina.User;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.ReviewRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity getById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity add(UserEntity user) {
        return userRepository.save(user);
    }
    public void delete(Long id) {
        userRepository.deleteById(Math.toIntExact(id));
    }

}
