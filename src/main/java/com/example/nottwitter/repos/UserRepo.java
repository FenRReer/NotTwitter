package com.example.nottwitter.repos;

import com.example.nottwitter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo  extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
