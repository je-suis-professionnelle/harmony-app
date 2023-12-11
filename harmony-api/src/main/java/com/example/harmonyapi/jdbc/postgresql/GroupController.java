package com.example.harmonyapi.jdbc.postgresql;

import com.example.harmonyapi.Group;
import com.example.harmonyapi.GroupUser;
import com.example.harmonyapi.User;
import com.example.harmonyapi.jdbc.postgresql.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:5173/") /* ça marche ce truc ??*/
@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupUserRepository groupUserRepository;


    @GetMapping("/byTitle")
    public ResponseEntity<List<Group>> getGroupsByTitleContaining(@RequestParam(required = true) String title) {
        try {
            List<Group> groups = new ArrayList<Group>();

            groupRepository.findByTitleContaining(title).forEach(groups::add);

            if (groups.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @GetMapping("/byPseudo")
    public ResponseEntity<List<Group>> getGroupsByPseudo(@RequestParam(required = true) String pseudo) {
        try {
            List<Group> groups = groupRepository.findByPseudosContaining(pseudo);

            if (groups.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @GetMapping("/byId")
    public ResponseEntity<List<Group>> getGroupsByIdUser(@RequestParam(required = true) long idUser) {
        try {
            List<Group> groups = groupRepository.findGroupsByIdUser(idUser);

            if (groups.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/groups")
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        try {
            // Save the new group
            Group savedGroup = groupRepository.save(group);

            // Create a GroupUser to associate the owner with the group
            User owner = userRepository.findById(group.getIdOwner()).orElseThrow(() -> new Exception("Owner not found"));
            GroupUser groupUser = new GroupUser(savedGroup, owner);
            groupUserRepository.save(groupUser);

            return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*
    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        try {
            Group _group = groupRepository
                    .save(new Group(group.getIdOwner(), group.getTitle()));
            return new ResponseEntity<>(_group, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    /*
    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        try {
            Group _group = groupRepository
                    .save(new Group(group.getPseudos(), group.getTitle()));
            return new ResponseEntity<>(_group, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     */

}
