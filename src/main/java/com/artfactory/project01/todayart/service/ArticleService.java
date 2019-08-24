package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Article;
import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.model.ArticleForm;
import com.artfactory.project01.todayart.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    /*
       작성자: 진표
       기능 : 새로운 게시글 생성
       @param Article
       @return Article
    */
    @Transactional
    public Article cretateArticle(Article article) {


        return articleRepository.save(article);
    }

    /*
       작성자: 진표
       기능 : 보드 아이디별 리스트페이지
       @param 보드ID(Integer)
       @return Page<Article>
    */
    @Transactional(readOnly = true)
    public Page<Article> listOfArticle(Integer boardId, Pageable pageable) {
        Page<Article> temp = articleRepository.findByBoardId(boardId, pageable);
        for(Article article:temp){
            article.setBoardName(article.getBoardCategory().getBoardName());
            article.setBoardId(article.getBoardCategory().getBoardId());
            article.setMemberId(article.getMember().getMemberId());
            article.setNickname(article.getMember().getNickname());
        }

        return temp;
    }

    /*
       작성자: 진표
       기능 : 조건별 검색
       @param value(검색값),boardCategory(찾는 보드아이디),where(제목,내용,아이디,제목+내용)
       @return 검색된 Page<Article>
    */
    @Transactional(readOnly = true)
    public Page<Article> search(String value, Integer boardId ,String where, Pageable pageable) {

        Page<Article> result = null;
        if(where.equals("title")){
            result = articleRepository.searchTitle(value , boardId , where , pageable);
        }else if(where.equals("content")){
            result = articleRepository.searchContent(value , boardId , where , pageable);
        }else if(where.equals("memberId")){
            result = articleRepository.searchMemberId(value , boardId , where , pageable);
        }else if(where.equals("TC")){
            result = articleRepository.searchTitleContent(value , boardId , where , pageable);
        }
        return result;
    }

    /*
       작성자: 진표
       기능 : 게시글 상세보기 및 뷰카운트 +1
       @param articleId
       @return articleId에 해당하는 게시글
    */
    @Transactional
    public Optional<Article> itemOfArticle(Integer id) {
        Article article = articleRepository.findById(id).get();

        article.setBoardName(article.getBoardCategory().getBoardName());
        article.setBoardId(article.getBoardCategory().getBoardId());
        article.setMemberId(article.getMember().getMemberId());
        article.setNickname(article.getMember().getNickname());

        //view 값을 가져와서  +1
        article.setViews(article.getViews()+1);
        articleRepository.save(article);

        return articleRepository.findById(id);
    }

    /*
       작성자: 진표
       기능 : 게시글 업데이트
       @param articleForm,articleId
       @return 업데이트 된 Article
    */
    @Transactional
    public Article updateArticle(Integer id, ArticleForm articleForm) {
        Article originalVO = articleRepository.findById(id).get();

        articleForm.setArticle(originalVO);
        return articleRepository.save(originalVO);
    }

    /*
       작성자: 진표
       기능 : 게시글 삭제(isDeleted = 1, 삭제시간생성)
       @param ArticleId
       @return Article
    */
    @Transactional
    public Article deleteArticle(Integer id) {
        Article originalVO = articleRepository.findById(id).get();
        originalVO.setIsDeleted("y");
        originalVO.setDeletedDated(new Date());
        return articleRepository.save(originalVO);
    }

    /*
       작성자: 진표
       기능 : 게시글 DB 삭제
       @param ArticleId
    */
    @Transactional
    public void dataDeleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }

}
