package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, String> {

    List<Label> findByIdGroup(long idGroup);
}
