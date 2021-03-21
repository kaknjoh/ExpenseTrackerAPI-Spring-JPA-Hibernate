package com.example.expensetracker.services;

import com.example.expensetracker.domain.User;
import com.example.expensetracker.exceptions.EtAuthException;
import com.example.expensetracker.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.regex.Pattern;


@Service
@Transactional
public class UserServiceImpl  implements  UserService{

    @Autowired
    UserRepository userRepository;



    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        try{
            if(email!=null)
                email=email.toLowerCase();
            User user= userRepository.findByEmail(email);

            if(!BCrypt.checkpw(password, user.getPassword())){
                throw new EtAuthException("Invalid credentials");
            }
            return user;
        }catch (Exception e){
            throw new EtAuthException("Invalid email/password");
        }


    }

    @Override
    public User registerUser(String email, String password, String firstName, String lastName) throws EtAuthException {
        String hashedPassword= BCrypt.hashpw(password, BCrypt.gensalt(10));
        Pattern pattern= Pattern.compile("^(.+)@(.+)$");
        if(email!=null){
            email=email.toLowerCase();

        }
        if(!pattern.matcher(email).matches())
            throw new EtAuthException("Invalid email format");

        long count=userRepository.countByEmail(email);
        if(count>0){
            throw new EtAuthException("Email already in use");
        }
        User user=new User(firstName, lastName, email, hashedPassword);
        userRepository.save(user);
        return user;
    }

    @Override
    public User findUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
