package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.BoardCategory;
import com.artfactory.project01.todayart.model.BoardCategoryForm;
import com.artfactory.project01.todayart.repository.BoardCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BoardCategoryService {

    @Autowired
    private BoardCategoryRepository boardCategoryRepository;

    /*
       작성자: 진표
       기능 : 새로운 게시판 카테고리 생성
       @param BoardCategory
       @return BoardCategory
    */
    @Transactional
    public BoardCategory createBoardCategory(BoardCategory boardCategory) {
        return boardCategoryRepository.save(boardCategory);
    }

    /*
       작성자: 진표
       기능 : 보드 카테고리 리스트
       @param 보드ID(Integer)
       @return Page<Article>
    */
    @Transactional(readOnly = true)
    public Page<BoardCategory> listOfBoardCategory(Pageable pageable) {
        return boardCategoryRepository.findAll(pageable);
    }

    /*
       작성자: 진표
       기능 : 보드 카테고리 업데이트
       @param BoardCategoryForm
       @return 업데이트 된 Article
    */
    @Transactional
    public BoardCategory updateBoardCategory(Integer id, BoardCategoryForm boardCategoryForm) {
        BoardCategory originBoardCategory = boardCategoryRepository.findById(id).get();

        boardCategoryForm.setBoardCategory(originBoardCategory);
        return boardCategoryRepository.save(originBoardCategory);
    }


    /*
       작성자: 진표
       기능 : 게시글 DB 삭제
       @param ArticleId
    */
    @Transactional
    public void deleteBoardCartegory(Integer id) {
        boardCategoryRepository.deleteById(id);
    }


}
