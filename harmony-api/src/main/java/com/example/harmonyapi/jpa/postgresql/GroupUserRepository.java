package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {

        public GroupUser findByPseudoUserAndIdGroup(String pseudoUser, long idGroup);

}
