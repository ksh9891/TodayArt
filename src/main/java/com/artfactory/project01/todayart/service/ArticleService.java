package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.ArticleVO;
import com.artfactory.project01.todayart.model.ArticleForm;
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

    @Transactional
    public Optional<ArticleVO> itemOfArticle(Integer id) {
        ArticleVO originalVO = articleRepository.findById(id).get();
        originalVO.setViews(originalVO.getViews()+1);
        articleRepository.save(originalVO);
        return articleRepository.findById(id);
    }

    @Transactional
    public ArticleVO updateArticle(Integer id, ArticleForm articleForm) {
        ArticleVO originalVO = articleRepository.findById(id).get();

        articleForm.setArticleVO(originalVO);
        return articleRepository.save(originalVO);
    }

    @Transactional
    public ArticleVO deleteArticle(Integer id) {
        ArticleVO originalVO = articleRepository.findById(id).get();
        originalVO.setIs_deleted(1);
        return articleRepository.save(originalVO);
    }

    @Transactional
    public void dataDeleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }

}
