package com.cg.ebug.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.ebug.entity.Project_Table;

public interface Project_Repository extends JpaRepository<Project_Table, Long> {
	Optional<Project_Table> findById(Long id);
}