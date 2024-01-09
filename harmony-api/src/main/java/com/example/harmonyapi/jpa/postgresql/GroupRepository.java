package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Group g JOIN GroupUser gu ON g.identifiant = gu.idGroup WHERE gu.pseudoUser = :pseudo")
    List<Group> findGroupsByPseudoUser(@Param("pseudo") String pseudo);
}