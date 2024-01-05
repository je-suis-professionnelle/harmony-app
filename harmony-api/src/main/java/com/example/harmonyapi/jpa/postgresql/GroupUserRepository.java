package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {

        public GroupUser findByPseudoUserAndIdGroup(String pseudoUser, long idGroup);

        public List<GroupUser> findByIdGroup(long idGroup);

}
