package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.ArticleVO;
import com.artfactory.project01.todayart.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    public ArticleVO cretateArticle(ArticleVO articleVO) {
        return articleRepository.save(articleVO);
    }

    @Transactional(readOnly = true)
    public Page<ArticleVO> listOfArticle(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<ArticleVO> itemOfArticle(Integer id) {
        return articleRepository.findById(id);
    }

    @Transactional
    public ArticleVO updateArticle(Integer id, ArticleVO articleVO) {
        return articleRepository.save(articleVO);
    }

    @Transactional
    public void deleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }

}
