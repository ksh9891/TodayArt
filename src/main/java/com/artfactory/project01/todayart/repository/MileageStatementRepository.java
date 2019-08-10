package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.MileageStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MileageStatementRepository extends JpaRepository<MileageStatement, Integer> {
}
