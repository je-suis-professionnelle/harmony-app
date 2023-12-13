package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT gu.group FROM GroupUser gu WHERE gu.user.id = :userId")
    List<Group> findGroupsByIdUser(@Param("userId") long userId);

    List<Group> findByTitleContaining(String title);

}