package com.artfactory.project01.todayart.controller;

import com.artfactory.project01.todayart.entity.Article;
import com.artfactory.project01.todayart.entity.BoardCategory;
import com.artfactory.project01.todayart.model.ArticleForm;
import com.artfactory.project01.todayart.model.BoardCategoryForm;
import com.artfactory.project01.todayart.model.ResultItems;
import com.artfactory.project01.todayart.service.BoardCategoryService;
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
@RequestMapping("/admin")
@CrossOrigin
@ResponseStatus(HttpStatus.OK)
public class BoardCategoryController {

    @Autowired
    private BoardCategoryService boardCategoryService;

    /*
        작성자: 진표
        기능 : 새로운 게시판 카테고리 생성
        @param Article
        @return Article
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(
            path = "/boardCreate",
            method = RequestMethod.POST,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public BoardCategory create(
            @RequestBody BoardCategory boardCategory) {

        return boardCategoryService.createBoardCategory(boardCategory);
    }

    /*
     작성자: 진표
     기능 : Board_id별 게시물 전체 출력
     @param Article
     @return 페이징 처리가된 Article List
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(
            path = "/boardList",
            method = RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResultItems<BoardCategory> listOf(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<BoardCategory> boardCategoryList = boardCategoryService.listOfBoardCategory(pageable);
        return new ResultItems<BoardCategory>(boardCategoryList.stream().collect(Collectors.toList()), page, size, boardCategoryList.getTotalElements());
    }


    /*
     작성자: 진표
     기능 : boardCategory 업데이트
     @param BoardCategory
     @return BoardCategory
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(
            path = "/{board_id}",
            method = RequestMethod.PATCH,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public BoardCategory update(@PathVariable("board_id") Integer id, @RequestBody BoardCategoryForm boardCategoryForm) {
        return boardCategoryService.updateBoardCategory(id, boardCategoryForm);
    }

    /*
     작성자: 진표
     기능 : board_id별 DB데이터 삭제
     @param boardCategory
     @return 삭제완료된 boardCategory
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(
            path = "/delete",
            method = RequestMethod.DELETE,
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public BoardCategory dataDelete(@PathVariable("board_id") Integer id) {
        boardCategoryService.deleteBoardCartegory(id);

        BoardCategory boardCategory = new BoardCategory();
        boardCategory.setBoardId(id);
        return boardCategory;
    }

}
