package com.example.harmonyapi.jdbc.postgresql;

import com.example.harmonyapi.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    //enlever
    /*
    @Query("SELECT * FROM groups WHERE pseudos LIKE %:pseudo%")
    List<Group> findByPseudosContaining();

     */

    /*@Query("SELECT * FROM groups WHERE idUser = :idUser")
    List<Group> findByGroupUserContainingIdUser(@Param("idUser") long idUser);

     */

    @Query("SELECT gu.group FROM GroupUser gu WHERE gu.user.id = :userId")
    List<Group> findGroupsByIdUser(@Param("userId") long userId);

    List<Group> findByTitleContaining(String title);

}