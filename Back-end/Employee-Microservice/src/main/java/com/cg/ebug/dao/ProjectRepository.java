package com.cg.ebug.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.ebug.entity.ProjectTable;

public interface ProjectRepository extends JpaRepository<ProjectTable, Long> {
	Optional<ProjectTable> findById(Long id);
}