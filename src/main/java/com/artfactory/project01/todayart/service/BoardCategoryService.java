package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.BoardCategory;
import com.artfactory.project01.todayart.model.BoardCategoryForm;
import com.artfactory.project01.todayart.repository.BoardCategoryRepository;
import com.artfactory.project01.todayart.util.PrincipalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
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
       @return Page<BoardCategory>
    */
    @Transactional(readOnly = true)
    public Page<BoardCategory> listOfBoardCategory(Pageable pageable) {
        return boardCategoryRepository.findAll(pageable);
    }

    /*
       작성자: 진표
       기능 : 보드 카테고리 업데이트
       @param BoardCategoryForm
       @return 업데이트 된 BoardCategoryForm
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
    public void deleteBoardCartegory(Integer id) throws Exception {
        if(id ==1 || id ==2) {
            throw new Exception("기본 게시판은 삭제할 수 없습니다");
        } else{
            boardCategoryRepository.deleteById(id);
        }
    }
}
