package com.springSecure.springSecure.repository;

import com.springSecure.springSecure.model.UserObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserObj,Long> {
   UserObj findByName(String name);
    UserObj findByEmail(String email);
    boolean existsByName(String name);
    boolean existsByEmail(String email);

}