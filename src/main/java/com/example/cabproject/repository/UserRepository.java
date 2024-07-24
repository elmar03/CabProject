package com.example.cabproject.repository;

import com.example.cabproject.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    User findByUsername(String username);

    User findUserByUserId(long userId);

    User findUserByEmailAddress(String emailAddress);


}
