package com.example.harmonyapi.jdbc.postgresql;

import com.example.harmonyapi.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    Optional<User> findByPseudo(String pseudo);
    /*List<User> findById(long id);

    List<User> findByPseudoContaining(String mot);*/

    /*
    Now we can use JpaRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()… without implementing these methods.

We also define custom finder methods:
– findByPublished(): returns all Tutorials with published having value as input published.
– findByTitleContaining(): returns all Tutorials which title contains input title.

The implementation is plugged in by Spring Data JPA automatically.
     */
/*
    int create(User user);

    int update(User user);

    User findByLogin(Long login);

    int deleteByLogin(Long login);

    List<User> getAll();

    List<User> findByPseudo(String pseudo);*/

    /*int deleteAll();*/
}
