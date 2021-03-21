package com.example.expensetracker.repositories;
/*
import com.example.expensetracker.domain.User;
import com.example.expensetracker.exceptions.EtAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements  UserRepository{

    private static final String SQL_COUNT_BY_EMAIL= "SELECT COUNT(*) FROM users WHERE EMAIL = ?";
    private static final String SQL_FIND_BY_ID=" SELECT user_id, firstName, lastName, email, password FROM users WHERE user_id=?";
    private static final String SQL_CREATE="INSERT INTO  users(user_id, firstName, lastName, email, password)  VALUES(NEXTVAL('users_SEQ'),?,?,?,?)";
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {
        try{
            KeyHolder keyHolder=new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps=connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4,password);

                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("user_id");
        }catch(Exception e){
            throw new EtAuthException("Invalid details. Failed to create account");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        return  null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, Integer.class, email) ;
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, userRowMapper,userId);
    }

    private RowMapper<User> userRowMapper=((rs, rowNum) -> {
        return new User(rs.getInt("user_id"), rs.getString("firstName"), rs.getString("lastName"),rs.getString("email"),
                rs.getString("password"));
    });
}
*/