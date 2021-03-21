package com.example.expensetracker.controllers;


import com.example.expensetracker.Constants;
import com.example.expensetracker.domain.User;
import com.example.expensetracker.exceptions.EtAuthException;
import com.example.expensetracker.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;



    @PostMapping("/login")
    public ResponseEntity<Map<String , String>> loginUser(@RequestBody Map<String, Object> userMap){
        String email=(String) userMap.get("email");
        String password=(String) userMap.get("password");
        User user=userService.validateUser(email, password);
        Map<String, String> map= new HashMap<>();
        if(user!=null){


            return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
        }else {
            map.put("message", "Invalid credentials");
            return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
        }






    }

    @PostMapping("/register")
    public ResponseEntity<Map<String , String>> registerUser(@RequestBody Map<String, Object> userMap){
        String firstName=(String) userMap.get("firstName");
        String lastName=(String) userMap.get("lastName");
        String email=(String) userMap.get("email");
        String password=(String) userMap.get("password");


        User user= userService.registerUser(email, password, firstName, lastName);


        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);

    }




    private Map<String, String> generateJWTToken(User user){
        long timestamp=System.currentTimeMillis();
        String token= Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .compact();
        Map<String , String> map=new HashMap<>();
        map.put("token", token);
        return map;


    }
}
