package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Comments;
import com.artfactory.project01.todayart.model.CommentForm;
import com.artfactory.project01.todayart.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


@Service
public class CommentService {


    @Autowired
    private CommentRepository commentRepository;

    //댓글 생성
    @Transactional
    public Comments createComments(Comments comments) {
        return commentRepository.save(comments);
    }


    //articleId 별 댓글 리스트 리턴
    @Transactional(readOnly = true)
    public Page<Comments> listOfComments(Integer id,Pageable pageable) {
        return commentRepository.findByArticleId(id, pageable);
    }


    //댓글 업데이트
    @Transactional
    public Comments updateCommments(Integer id, CommentForm commentForm) {
        Comments comments = commentRepository.findById(id).get();

        commentForm.setComments(comments);
        return commentRepository.save(comments);
    }

    //댓글 삭제시 isDelete =1 로 변경
    @Transactional
    public Comments deleteComments(Integer id) {
        Comments comments = commentRepository.findById(id).get();
        comments.setIsDelete(1);
        comments.setDeleteDated(new Date());
        return commentRepository.save(comments);
    }

    //댓글 삭제
    @Transactional
    public void dataDeleteComments(Integer id) {
        commentRepository.deleteById(id);
    }


}
