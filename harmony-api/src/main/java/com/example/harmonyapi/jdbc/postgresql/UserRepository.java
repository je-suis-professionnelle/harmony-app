package com.example.harmonyapi.jdbc.postgresql;

import com.example.harmonyapi.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    int create(User user);

    int update(User user);

    User findByLogin(Long login);

    int deleteByLogin(Long login);

    List<User> getAll();

    List<User> findByPseudo(String pseudo);

    int deleteAll();
}
