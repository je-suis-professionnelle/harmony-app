package com.example.harmonyapi.jdbc.postgresql;

import com.example.harmonyapi.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*@CrossOrigin("http://127.0.0.1:5500/")*/
@CrossOrigin("http://localhost:5173/") /* ça marche ce truc ??*/
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> get(@RequestParam(required = false) String pseudo) {
        try {
            List<User> users = new ArrayList<User>();

            if (pseudo == null)
                userRepository.findAll().forEach(users::add);
                /*System.out.println("getAll");*/
            else
                /*userRepository.findByPseudo(pseudo).forEach(users::add);*/
                System.out.println("findByPseudo "+pseudo);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User _user = userRepository
                    .save(new User(user.getMdp(), user.getPseudo()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> logUser(@RequestBody User user) {
        try {
            // Récupérer l'utilisateur par le pseudo
            Optional<User> optionalUser = userRepository.findByPseudo(user.getPseudo());

            // Vérifier si l'utilisateur existe
            if (optionalUser.isPresent()) {
                User _user = optionalUser.get();

                // Vérifier le mot de passe
                if (_user.getMdp().equals(user.getMdp())) {
                    return new ResponseEntity<>(_user, HttpStatus.OK);
                } else {
                    // Mot de passe incorrect
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                }
            } else {
                // Utilisateur non trouvé
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Erreur interne du serveur
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@GetMapping("/users/{id}")
    public ResponseEntity<User> getUserByLogin(@PathVariable("login") long login) {
        User tutorial = userRepository.findByLogin(login);

        if (tutorial != null) {
            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            userRepository.create(new User(user.getIdentifiant(), user.getMdp(), user.getPseudo()));
            return new ResponseEntity<>("User was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("User was not created.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    /*@PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") long id, @RequestBody User tutorial) {
        User _tutorial = userRepository.findById(id);

        if (_tutorial != null) {
            _tutorial.setId(id);
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());

            userRepository.update(_tutorial);
            return new ResponseEntity<>("User was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find User with id=" + id, HttpStatus.NOT_FOUND);
        }
    }*/
}