package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Article;
import com.artfactory.project01.todayart.model.ArticleForm;
import com.artfactory.project01.todayart.model.ResultItems;
import com.artfactory.project01.todayart.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Page<Article> listOfArticle1(Pageable pageable) {
        return articleRepository.findByBoard_id1(1, pageable);
    }

    //보드아이디가 2인 리스트 리턴
    @Transactional(readOnly = true)
    public Page<Article> listOfArticle2(Pageable pageable) {
        return articleRepository.findByBoard_id2(2, pageable);
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
