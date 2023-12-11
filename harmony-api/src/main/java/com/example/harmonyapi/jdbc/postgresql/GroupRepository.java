package com.example.harmonyapi.jdbc.postgresql;

import com.example.harmonyapi.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    public List<Group> findByPseudosContaining(String pseudo);
    List<Group> findByTitleContaining(String title);

}
