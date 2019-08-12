package com.artfactory.project01.todayart.controller;


import com.artfactory.project01.todayart.entity.ArticleVO;
import com.artfactory.project01.todayart.model.ArticleForm;
import com.artfactory.project01.todayart.model.ResultItems;
import com.artfactory.project01.todayart.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
@CrossOrigin
@ResponseStatus(HttpStatus.OK)
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //게시물 생성
    @RequestMapping(
            value = "create",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ArticleVO create(
            @RequestBody ArticleVO articleVO) {

        return articleService.cretateArticle(articleVO);
    }

    //리스트 불러오기 (카테고리별 커스텀 쿼리 해야함)
    @RequestMapping(
            value = "list1",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResultItems<ArticleVO> listOf1(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ArticleVO> articleList = articleService.listOfArticle1(pageable);
        return new ResultItems<ArticleVO>(articleList.stream().collect(Collectors.toList()), page, size, articleList.getTotalElements());
    }

    @RequestMapping(
            value = "list2",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResultItems<ArticleVO> listOf2(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ArticleVO> articleList = articleService.listOfArticle2(pageable);
        return new ResultItems<ArticleVO>(articleList.stream().collect(Collectors.toList()), page, size, articleList.getTotalElements());
    }

    //게시물 1개 보기
    @RequestMapping(
            path = "/{article_id}",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ArticleVO retrieve(@PathVariable("article_id") Integer id) {
        return articleService.itemOfArticle(id).get();
    }

    //게시물 업데이트
    @RequestMapping(
            path = "/{article_id}",
            method = RequestMethod.PATCH,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ArticleVO update(@PathVariable("article_id") Integer id, @RequestBody ArticleForm articleForm) {
//        articleVO.setArticle_id(id);
        return articleService.updateArticle(id, articleForm);
    }

    //삭제하기(isdelete =0 에서 1로)
    @RequestMapping(
            path = "/{article_id}",
            method = RequestMethod.DELETE,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ArticleVO delete(@PathVariable("article_id") Integer id) {

        return articleService.deleteArticle(id);
    }

    //데이터 DB삭제
    @RequestMapping(
            path = "admin/{article_id}",
            method = RequestMethod.DELETE,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ArticleVO dataDelete(@PathVariable("article_id") Integer id) {
        articleService.dataDeleteArticle(id);

        ArticleVO articleVO = new ArticleVO();
        articleVO.setArticle_id(id);
        return articleVO;
    }

//    @RequestMapping(
//            path = "/search/{value}",
//            method = RequestMethod.GET,
//            produces = {
//                    MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    MediaType.APPLICATION_XML_VALUE
//            }
//    )
//    public ArticleVO search
}