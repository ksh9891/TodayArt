package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.ArticleVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleVO,Integer> {

}
