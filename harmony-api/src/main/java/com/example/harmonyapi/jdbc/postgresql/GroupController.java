package com.example.harmonyapi.jdbc.postgresql;

import com.example.harmonyapi.Group;
import com.example.harmonyapi.User;
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
    }

    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        try {
            Group _group = groupRepository
                    .save(new Group(group.getPseudos(), group.getTitle()));
            return new ResponseEntity<>(_group, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
