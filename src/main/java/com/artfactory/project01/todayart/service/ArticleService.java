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

    //조건별검색
    @Transactional(readOnly = true)
    public Page<Article> search(String value, Integer boardId ,String where, Pageable pageable) {

        if(where.equals("title")){
            return articleRepository.searchTitle(value , boardId , where , pageable);
        }else if(where.equals("content")){
            return articleRepository.searchContent(value , boardId , where , pageable);
        }else if(where.equals("memberId")){
            return articleRepository.searchMemberId(value , boardId , where , pageable);
        }else if(where.equals("title")){
            return articleRepository.searchTitle(value , boardId , where , pageable);
        }else if(where.equals("TC")){
            return articleRepository.searchTitleContent(value , boardId , where , pageable);
        }
        return null;
    }

    //게시물 상세보기(view +1)
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
