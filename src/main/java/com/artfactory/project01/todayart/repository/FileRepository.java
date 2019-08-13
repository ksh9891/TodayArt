package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    File findByFileName(String fileName);
}
