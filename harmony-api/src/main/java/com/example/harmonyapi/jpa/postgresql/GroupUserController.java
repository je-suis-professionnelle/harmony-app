package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.Expense;
import com.example.harmonyapi.model.GroupUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") /* ça marche ce truc ??*/
@RestController
public class GroupUserController {
    @Autowired
    GroupUserRepository groupUserRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/groupUser")
    public ResponseEntity<GroupUser> getGroupUserByPseudoAndIdGroup(@RequestParam(required = true) String pseudoUser, @RequestParam(required = true) int idGroup) {
        try {
            GroupUser groupUser = groupUserRepository.findByPseudoUserAndIdGroup(pseudoUser, idGroup);
            return new ResponseEntity<>(groupUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/groupUsers")
    public ResponseEntity<List<GroupUser>> getGroupUserByIdGroup(@RequestParam(required = true) int idGroup) {
        try {
            List<GroupUser> groupUsers = groupUserRepository.findByIdGroup(idGroup);
            return new ResponseEntity<>(groupUsers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/groupUser")
    public ResponseEntity<GroupUser> addGroupUser(@RequestBody GroupUser groupUser) {
        try {
            if (userAlreadyInGroup(groupUser.getIdGroup(), groupUser.getPseudoUser())) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }

            GroupUser savedGroupUser = groupUserRepository.save(groupUser);
            return new ResponseEntity<>(savedGroupUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean userAlreadyInGroup(long idGroup, String pseudoUser) {
        return groupUserRepository.findByPseudoUserAndIdGroup(pseudoUser, idGroup) != null;
    }

    private boolean userDoesntExist(String pseudoUser) {
        return userRepository.findByPseudo(pseudoUser).isEmpty();
    }

    @DeleteMapping("/groupUser")
    public ResponseEntity<Expense> deleteGroupUser(@RequestParam(required = true) String pseudoUser, @RequestParam(required = true) int idGroup) {
        try {
            if(userDoesntExist(pseudoUser)) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            if(!userAlreadyInGroup(idGroup, pseudoUser)) {
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            }
            GroupUser groupUser = groupUserRepository.findByPseudoUserAndIdGroup(pseudoUser, idGroup);
            groupUserRepository.delete(groupUser);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
