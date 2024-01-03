package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.Group;
import com.example.harmonyapi.model.GroupUser;
import com.example.harmonyapi.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")/* ça marche ce truc ??*/
@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupUserRepository groupUserRepository;

    @CrossOrigin(origins = "*")/* ça marche ce truc ??*/
    @RequestMapping(
            value = "/**",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity handle() {
        System.out.println("handle OPTIONS");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Group>> getGroups() {
        try {
            System.out.println("getGroups");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("authentication: " + (UserDetails) authentication.getPrincipal());
            if (authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
                String username = ((UserDetails) authentication.getPrincipal()).getUsername();
                System.out.println("username: " + username);

                List<Group> groups;

                // Utilise le username pour obtenir les groupes
                groups = groupRepository.findGroupsByPseudoUser(username);

                if (groups.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(groups, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/groups")
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        try {
            Group savedGroup = groupRepository.save(group);

            User owner = userRepository.findByPseudo(group.getPseudoOwner()).orElseThrow(() -> new Exception("Owner not found"));
            GroupUser groupUser = new GroupUser(group.getPseudoOwner(), group.getIdentifiant());
            groupUserRepository.save(groupUser);

            return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @PostMapping("/groups")
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        try {
            Group savedGroup = groupRepository.save(group);

            User owner = userRepository.findByPseudo(group.getPseudoOwner()).orElseThrow(() -> new Exception("Owner not found"));
            GroupUser groupUser = new GroupUser(savedGroup, owner);
            groupUserRepository.save(groupUser);

            return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
}
