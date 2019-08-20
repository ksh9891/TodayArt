package com.artfactory.project01.todayart.controller;


import com.artfactory.project01.todayart.entity.Article;
import com.artfactory.project01.todayart.entity.Comments;
import com.artfactory.project01.todayart.model.CommentForm;
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
@RequestMapping("/comment")
@CrossOrigin
@ResponseStatus(HttpStatus.OK)
public class CommentController {

    @Autowired
    private CommentService commentService;

    /*
        작성자: 진표
        기능 : 새로운 댓글 생성
        @param Comment
        @return Comment
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST', 'ADMIN')")
    @RequestMapping(
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public Comments create(
            @RequestBody Comments comments) {

        return commentService.createComments(comments);
    }

    /*
     작성자: 진표
     기능 : Artcle_Id별 댓글 전체 출력
     @param Comments
     @return 페이징 처리가된 Comments List
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST', 'ADMIN')")
    @RequestMapping(
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResultItems<Comments> listOf(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size, Integer articleId) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Comments> commentList = commentService.listOfComments(articleId, pageable);
        return new ResultItems<Comments>(commentList.stream().collect(Collectors.toList()), page, size, commentList.getTotalElements());
    }


    /*
     작성자: 진표
     기능 : comment_id로 찾은 댓글 업데이트
     @param Comments
     @return Comments
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST', 'ADMIN')")
    @RequestMapping(
            path = "/{commentId}",
            method = RequestMethod.PATCH,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public Comments update(@PathVariable("commentId") Integer id, @RequestBody CommentForm commentForm) {
        return commentService.updateCommments(id, commentForm);
    }

    /*
     작성자: 진표
     기능 : Comments의  is_delete의 값을 1로 수정
     @param Comments
     @return Comments
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ARTIST', 'ADMIN')")
    @RequestMapping(
            path = "/{commentId}",
            method = RequestMethod.DELETE,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public Comments delete(@PathVariable("commentId") Integer id) {
        return commentService.deleteComments(id);
    }

    /*
     작성자: 진표
     기능 : commentId별 DB데이터 삭제
     @param Comments
     @return 삭제완료된 Comments
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(
            path = "/admin/{commentId}",
            method = RequestMethod.DELETE,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public Comments dataDelete(@PathVariable("commentId") Integer id) {
        commentService.dataDeleteComments(id);

        Comments comments = new Comments();
        comments.setCommentId(id);
        return comments;
    }


    /*
     작성자: 진표
     기능 : 전체 댓글 검색(내용,유져아이디) //어드민전용
     @param value(검색값),boardId(찾는 보드아이디),where(내용,아이디)
     @return 해당조건의 Page<Comments>
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(
            path = "/search",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResultItems<Comments> search(
            @RequestParam (name = "value") String value,
            @RequestParam (name = "boardId") Integer boardId,
            @RequestParam (name = "page", defaultValue = "1", required = false) int page,
            @RequestParam (name = "size", defaultValue = "10", required = false) int size,
            @RequestParam (name = "where") String where) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Comments> commentList = commentService.search(value,boardId,where,pageable);
        return new ResultItems<Comments>(commentList.stream().collect(Collectors.toList()), page, size, commentList.getTotalElements());
    }

}