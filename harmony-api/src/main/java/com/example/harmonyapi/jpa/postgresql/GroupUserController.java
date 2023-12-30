package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.Expense;
import com.example.harmonyapi.model.GroupUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*") /* ça marche ce truc ??*/
@RestController
@RequestMapping("/groupUser")
public class GroupUserController {
    @Autowired
    GroupUserRepository groupUserRepository;

    @GetMapping()
    public ResponseEntity<GroupUser> getGroupUserByPseudoAndIdGroup(@RequestParam(required = true) String pseudoUser, @RequestParam(required = true) int idGroup) {
        try {
            GroupUser groupUser = groupUserRepository.findByPseudoUserAndIdGroup(pseudoUser, idGroup);
            return new ResponseEntity<>(groupUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<GroupUser> addGroupUser(@RequestBody GroupUser groupUser) {
        try {
            GroupUser savedGroupUser = groupUserRepository.save(groupUser);
            return new ResponseEntity<>(savedGroupUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<Expense> deleteGroupUser(@RequestParam(required = true) String pseudoUser, @RequestParam(required = true) int idGroup) {
        try {
            GroupUser groupUser = groupUserRepository.findByPseudoUserAndIdGroup(pseudoUser, idGroup);
            groupUserRepository.delete(groupUser);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
