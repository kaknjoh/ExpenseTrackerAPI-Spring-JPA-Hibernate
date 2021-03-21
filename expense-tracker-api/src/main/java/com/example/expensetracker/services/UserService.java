package com.example.expensetracker.services;

import com.example.expensetracker.domain.User;
import com.example.expensetracker.exceptions.EtAuthException;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;

    User findUserById(Integer userId);

}
