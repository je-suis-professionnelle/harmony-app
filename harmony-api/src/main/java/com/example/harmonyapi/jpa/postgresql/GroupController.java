package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.Group;
import com.example.harmonyapi.model.GroupUser;
import com.example.harmonyapi.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*@GetMapping("/byTitle")
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
    }*/

    @GetMapping()
    public ResponseEntity<List<Group>> getGroups(@RequestParam(required = false) String pseudoUser, @RequestParam(required = false) String title) {
        try {
            List<Group> groups;
            if (pseudoUser != null) {
                groups = groupRepository.findGroupsByPseudoUser(pseudoUser);
            /* } else if (title != null) {
                groups = groupRepository.findByTitleContainingAndPseudoUser(title, pseudoUser);*/
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            if (groups.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@GetMapping()
    public ResponseEntity<List<Group>> getGroupsByPseudoUser(@RequestParam(required = true) String pseudoUser) {
        try {
            List<Group> groups = groupRepository.findGroupsByPseudoUser(pseudoUser);

            if (groups.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

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

}
