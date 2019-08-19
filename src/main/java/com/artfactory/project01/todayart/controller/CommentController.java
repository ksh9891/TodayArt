//package com.artfactory.project01.todayart.controller;
//
//import com.artfactory.project01.todayart.entity.Article;
//import com.artfactory.project01.todayart.entity.Comments;
//import com.artfactory.project01.todayart.model.ArticleForm;
//import com.artfactory.project01.todayart.model.ResultItems;
//import com.artfactory.project01.todayart.service.ArticleService;
//import com.artfactory.project01.todayart.service.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/article")
//@CrossOrigin
//@ResponseStatus(HttpStatus.OK)
//public class CommentController {
//
//    @Autowired
//    private ArticleService articleService;
//    @Autowired
//    private CommentService commentService;
//
//    /*
//        작성자: 진표
//        기능 : 새로운 게시글 생성
//        @param Article
//        @return Article
//     */
//    @RequestMapping(
//            value = "create/comment",
//            method = RequestMethod.POST,
//            produces = {
//                    MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    MediaType.APPLICATION_XML_VALUE
//            }
//    )
//    public Comments create(
//            @RequestBody Comments comments) {
//
//        return commentService.createComments(comments);
//    }
//
////
////    /*
////     작성자: 진표
////     기능 : Board_id가 2인 게시물 전체 출력
////     @param Article
////     @return 페이징 처리가된 Article List
////     */
////    @RequestMapping(
////            value = "list2",
////            method = RequestMethod.GET,
////            produces = {
////                    MediaType.APPLICATION_JSON_UTF8_VALUE,
////                    MediaType.APPLICATION_XML_VALUE
////            }
////    )
////    public ResultItems<Article> listOf2(
////            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
////            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
////        Pageable pageable = PageRequest.of(page - 1, size);
////        Page<Article> articleList = articleService.listOfArticle2(pageable);
////        return new ResultItems<Article>(articleList.stream().collect(Collectors.toList()), page, size, articleList.getTotalElements());
////    }
//
//    /*
//     작성자: 진표
//     기능 : article_id로 찾은 게시글 상세 표기
//     @param Article
//     @return Article
//     */
//    @RequestMapping(
//            path = "/{article_id}",
//            method = RequestMethod.GET,
//            produces = {
//                    MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    MediaType.APPLICATION_XML_VALUE
//            }
//    )
//    public ResultItems<Comments> listOf(
//            @PathVariable("article_id") Integer id,
//            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
//            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<Comments> commentList = commentService.listOfComments(id, pageable);
//        return new ResultItems<Comments>(commentList.stream().collect(Collectors.toList()), page, size, commentList.getTotalElements());
//    }
//
//    /*
//     작성자: 진표
//     기능 : article_id로 찾은 게시글 업데이트
//     @param Article
//     @return Article
//     */
//    @RequestMapping(
//            path = "/{article_id}",
//            method = RequestMethod.PATCH,
//            produces = {
//                    MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    MediaType.APPLICATION_XML_VALUE
//            }
//    )
//    public Article update(@PathVariable("article_id") Integer id, @RequestBody ArticleForm articleForm) {
//        return articleService.updateArticle(id, articleForm);
//    }
//
//    /*
//     작성자: 진표
//     기능 : article_id별 is_delete의 값을 1로 수정
//     @param Article
//     @return Article
//     */
//    @RequestMapping(
//            path = "/{article_id}",
//            method = RequestMethod.DELETE,
//            produces = {
//                    MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    MediaType.APPLICATION_XML_VALUE
//            }
//    )
//    public Article delete(@PathVariable("article_id") Integer id) {
//        return articleService.deleteArticle(id);
//    }
//
//    /*
//     작성자: 진표
//     기능 : article_id별 DB데이터 삭제
//     @param Article
//     @return 기능적용후 제외된 Article
//     */
//    @RequestMapping(
//            path = "admin/{article_id}",
//            method = RequestMethod.DELETE,
//            produces = {
//                    MediaType.APPLICATION_JSON_UTF8_VALUE,
//                    MediaType.APPLICATION_XML_VALUE
//            }
//    )
//    public Article dataDelete(@PathVariable("article_id") Integer id) {
//        articleService.dataDeleteArticle(id);
//
//        Article article = new Article();
//        article.setArticleId(id);
//        return article;
//    }
//}