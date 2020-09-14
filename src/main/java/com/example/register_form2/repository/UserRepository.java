package com.example.register_form2.repository;

import com.example.register_form2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    @Query(value = "Select id from user  WHERE login like ?1", nativeQuery = true)
    Long selectUserIdByUserLogin(String login);

    @Query(value = "Select email from user  WHERE login like ?1", nativeQuery = true)
    String selectUserEmailByUserLogin(String login);
}
