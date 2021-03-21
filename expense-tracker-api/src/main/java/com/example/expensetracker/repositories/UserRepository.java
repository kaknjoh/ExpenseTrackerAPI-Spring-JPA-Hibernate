package com.example.expensetracker.repositories;

import com.example.expensetracker.domain.User;
import com.example.expensetracker.exceptions.EtAuthException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    //Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

    User save(User user);

    User findByEmailAndPassword(String email, String password);

    Integer countByEmail(String email);

    User findByEmail(String email);

   // User findById(Integer userid);
}
