package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardCategoryRepository extends JpaRepository<BoardCategory,Integer> {
    @Query(value = "SELECT * FROM board_category WHERE board_id = ?1", nativeQuery = true)
    Optional<BoardCategory> findByBoardId(Integer articleId);
}
