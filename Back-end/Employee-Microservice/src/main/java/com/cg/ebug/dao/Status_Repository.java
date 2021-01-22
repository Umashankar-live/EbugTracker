package com.cg.ebug.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.ebug.entity.Status_Table;

public interface Status_Repository extends JpaRepository<Status_Table, Long> {

}
