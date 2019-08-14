package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Article;
import com.artfactory.project01.todayart.model.ArticleForm;
import com.artfactory.project01.todayart.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    //게시물 생성
    @Transactional
    public Article cretateArticle(Article article) {
        return articleRepository.save(article);
    }

    //보드아이디가 1인 리스트 리턴
    @Transactional(readOnly = true)
    public Page<Article> listOfArticle(Integer boardId, Pageable pageable) {
        return articleRepository.findByBoard_id(boardId, pageable);
    }

//    //보드아이디가 2인 리스트 리턴
//    @Transactional(readOnly = true)
//    public Page<Article> listOfArticle2(Pageable pageable) {
//        return articleRepository.findByBoard_id2(2, pageable);
//    }

    //타이틀명 검색
    @Transactional(readOnly = true)
    public Page<Article> findTitle(String title, Integer boardId, Pageable pageable) {
        return articleRepository.findByTitleAndAndBoardId(title , boardId , pageable);
    }


    @Transactional
    public Optional<Article> itemOfArticle(Integer id) {
        Article originalVO = articleRepository.findById(id).get();
        originalVO.setViews(originalVO.getViews()+1);
        articleRepository.save(originalVO);
        return articleRepository.findById(id);
    }

    @Transactional
    public Article updateArticle(Integer id, ArticleForm articleForm) {
        Article originalVO = articleRepository.findById(id).get();

        articleForm.setArticle(originalVO);
        return articleRepository.save(originalVO);
    }

    @Transactional
    public Article deleteArticle(Integer id) {
        Article originalVO = articleRepository.findById(id).get();
        originalVO.setIsDeleted(1);
        originalVO.setDeletedDated(new Date());
        return articleRepository.save(originalVO);
    }

    @Transactional
    public void dataDeleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }



}
