package org.example.technologie_sieciowe_1.infrastructure.repositories;

import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
//public interface UserRepository  extends CrudRepository<UserEntity,Integer> {
//    @Query("SELECT u FROM UserEntity u WHERE u.name = :loginForm")
//    static UserEntity findByUserName(String userName) {
//
//
//
//        return null;
//    }
//}
@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {

    //User findByUserName(String userName);
//    @Query(value = "SELECT u FROM user u WHERE u.name = :loginForm")
//    UserEntity findByUserName(String userName);   to jest w auth

}