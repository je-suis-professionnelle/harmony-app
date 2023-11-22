package com.example.harmonyapi.jdbc.postgresql;

import com.example.harmonyapi.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(User user) {
        return jdbcTemplate.update("INSERT INTO public.user (identifiant, mdp, pseudo) VALUES(?,?,?)",
                new Object[] { user.getIdentifiant(), user.getMdp(), user.getPseudo() });
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("UPDATE public.user SET identifiant=?, mdp=?, pseudo=? WHERE identifiant=?)",
                new Object[] { user.getIdentifiant(), user.getMdp(), user.getPseudo(), user.getIdentifiant() });
    }

    @Override
    public User findByLogin(Long login) {
        return null;
    }

    @Override
    public int deleteByLogin(Long login) {
        return 0;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM public.user", BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public List<User> findByPseudo(String pseudo) {
        return null;
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
