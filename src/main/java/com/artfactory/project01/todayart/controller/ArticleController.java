package com.artfactory.project01.todayart.controller;


import com.artfactory.project01.todayart.entity.ArticleVO;
import com.artfactory.project01.todayart.model.ResultItems;
import com.artfactory.project01.todayart.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

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

    @RequestMapping(
            value = "list",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResultItems<ArticleVO> listOf(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ArticleVO> articleList = articleService.listOfArticle(pageable);
        return new ResultItems<ArticleVO>(articleList.stream().collect(Collectors.toList()), page, size, articleList.getTotalElements());
    }

    @RequestMapping(
            path = "/{article_id}",
            method = RequestMethod.PATCH,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ArticleVO update(@PathVariable("article_id") Integer id, @RequestBody ArticleVO articleVO) {
        articleVO.getArticle_id(id);
        return articleService.updateArticle(id, articleVO);
    }
//
//    @PreAuthorize("hasAnyAuthority('WRITE_TODO')")
//    @RequestMapping(
//            path = "/{id}",
//            method = RequestMethod.PATCH,
//            produces = {
//                    MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    MediaType.APPLICATION_XML_VALUE
//            }
//    )
//    public Todo update(@PathVariable("id") String id, @RequestBody Todo todo) {
//        return todoService.updateTodo(id, todo);
//    }
//
//    @PreAuthorize("hasAnyAuthority('WRITE_TODO')")
//    @RequestMapping(
//            path = "/{id}",
//            method = RequestMethod.DELETE,
//            produces = {
//                    MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    MediaType.APPLICATION_XML_VALUE
//            }
//    )
//    public Todo delete(@PathVariable("id") String id) {
//        todoService.deleteTodo(id);
//
//        Todo todo = new Todo();
//        todo.setId(id);
//        return todo;
//    }
}