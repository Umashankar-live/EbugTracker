package com.cg.ebug.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.ebug.entity.CriticalLevel_Table;

public interface CriticalLevel_Repository  extends JpaRepository<CriticalLevel_Table, Long> {

}