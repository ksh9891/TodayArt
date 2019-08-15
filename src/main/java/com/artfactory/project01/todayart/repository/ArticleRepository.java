package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {

    //게시판 아이디가 1인게시물만 가져오기기
   @Query(value = "SELECT * FROM article WHERE board_id = 1 AND is_deleted = 0 ORDER BY write_dated DESC ", nativeQuery = true)
   Page<Article> findByBoard_id1(Integer board_id , Pageable pageable);
   @Query(value = "SELECT * FROM article WHERE board_id = 2 AND is_deleted = 0 ORDER BY write_dated DESC ", nativeQuery = true)
   Page<Article> findByBoard_id2(Integer board_id , Pageable pageable);
}
