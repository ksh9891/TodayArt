package com.artfactory.project01.todayart.controller;


import com.artfactory.project01.todayart.entity.Article;
import com.artfactory.project01.todayart.model.ArticleForm;
import com.artfactory.project01.todayart.model.ResultItems;
import com.artfactory.project01.todayart.service.ArticleService;
import com.artfactory.project01.todayart.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
@CrossOrigin
@ResponseStatus(HttpStatus.OK)
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    /*
        작성자: 진표
        기능 : 새로운 게시글 생성
        @param Article
        @return Article
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST', 'ADMIN')")
    @RequestMapping(
            path = "/create",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public Article create(
            @RequestBody Article article) {

        return articleService.cretateArticle(article);
    }

    /*
     작성자: 진표
     기능 : Board_id별 게시물 전체 출력
     @param Article
     @return 페이징 처리가된 Article List
     */
    @PreAuthorize("isAnonymous()")
    @RequestMapping(
            path = "/list",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResultItems<Article> listOf(
            @RequestParam(name = "boardId") Integer boardId,
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Article> articleList = articleService.listOfArticle(boardId, pageable);
        return new ResultItems<Article>(articleList.stream().collect(Collectors.toList()), page, size, articleList.getTotalElements());
    }

    /*
     작성자: 진표
     기능 : article_id로 찾은 게시글 상세 표기
     @param Article
     @return Article
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST', 'ADMIN')")
    @RequestMapping(
            path = "/{article_id}",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public Article retrieve(@PathVariable("article_id") Integer id){
        return articleService.itemOfArticle(id).get();
    }

    /*
     작성자: 진표
     기능 : article_id로 찾은 게시글 업데이트
     @param Article
     @return Article
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST', 'ADMIN')")
    @RequestMapping(
            path = "/{article_id}",
            method = RequestMethod.PATCH,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public Article update(@PathVariable("article_id") Integer id, @RequestBody ArticleForm articleForm) {
        return articleService.updateArticle(id, articleForm);
    }

    /*
     작성자: 진표
     기능 : article_id별 is_delete의 값을 1로 수정
     @param Article
     @return Article
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST', 'ADMIN')")
    @RequestMapping(
            path = "/{article_id}",
            method = RequestMethod.DELETE,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public Article delete(@PathVariable("article_id") Integer id) {
        return articleService.deleteArticle(id);
    }

    /*
     작성자: 진표
     기능 : article_id별 DB데이터 삭제
     @param Article
     @return 삭제완료된 Article
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(
            path = "/admin/{article_id}",
            method = RequestMethod.DELETE,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public Article dataDelete(@PathVariable("article_id") Integer id) {
        articleService.dataDeleteArticle(id);

        Article article = new Article();
        article.setArticleId(id);
        return article;
    }


    /*
     작성자: 진표
     기능 : 검색조건별 검색(제목,내용,유져아이디,제목+내용)
     @param value(검색값),boardCategory(찾는 보드아이디),where(제목,내용,아이디,제목+내용)
     @return 해당조건의 Page<Article>
     */
    @RequestMapping(
            path = "/search",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResultItems<Article> search(
            @RequestParam (name = "value") String value,
            @RequestParam (name = "boardId") Integer boardId,
            @RequestParam (name = "page", defaultValue = "1", required = false) int page,
            @RequestParam (name = "size", defaultValue = "10", required = false) int size,
            @RequestParam (name = "where") String where) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Article> articleList = articleService.search(value,boardId,where,pageable);
        return new ResultItems<Article>(articleList.stream().collect(Collectors.toList()), page, size, articleList.getTotalElements());
    }

}