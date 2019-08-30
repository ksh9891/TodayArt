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

    /*
       작성자: 진표
       기능 : 새로운 댓글 생성
       @param Comments
       @return Comments
    */
    @Transactional
    public Comments createComments(Comments comments) {
        comments.setGroup(comments.getArticleId());
        return commentRepository.save(comments);
    }

    @Transactional
    public Comments replyComments(Integer id,Comments comments) {
        Comments temp = commentRepository.findById(id).get();
        comments.setGroup(temp.getGroup());
        comments.setOrder(temp.getOrder()+1);
        comments.setDepth(temp.getDepth()+1);
        return commentRepository.save(comments);
    }
    /*
       작성자: 진표
       기능 : articleId별 Comments리스트 리턴
       @param articleId
       @return Page<Comments>
    */
    @Transactional(readOnly = true)
    public Page<Comments> listOfComments(Integer id, Pageable pageable) {
        Page<Comments> temp = commentRepository.findByArticleId(id, pageable);
        for(Comments comments:temp){
            comments.setMemberId(comments.getMember().getMemberId());
            comments.setNickname(comments.getMember().getNickname());
        }
        return temp;
    }


    /*
       작성자: 진표
       기능 : 댓글 업데이트
       @param CommentId,commentForm
       @return comments
    */
    @Transactional
    public Comments updateCommments(Integer id, CommentForm commentForm) {
        Comments comments = commentRepository.findById(id).get();

        commentForm.setComments(comments);
        return commentRepository.save(comments);
    }

    /*
       작성자: 진표
       기능 : 댓글 삭제(isDelete = 1, 삭제시 시각 업데이트)
       @param commentId
       @return Comments
    */
    @Transactional
    public Comments deleteComments(Integer id) {
        Comments comments = commentRepository.findById(id).get();
        comments.setIsDeleted("y");
        comments.setDeleteDated(new Date());
        return commentRepository.save(comments);
    }

    /*
       작성자: 진표
       기능 : 댓글 DB삭제
       @param commentId
    */
    @Transactional
    public void dataDeleteComments(Integer id) {
        commentRepository.deleteById(id);
    }

    /*
      작성자: 진표
      기능 : 조건별 검색
      @param value(검색값),boardCategory(찾는 보드아이디),where(내용,아이디)
      @return 검색된 Page<Article>
   */
    @Transactional(readOnly = true)
    public Page<Comments> search(String value, Integer boardId , String where, Pageable pageable) {

        Page<Comments> result = null;
        if(where.equals("content")){
            result = commentRepository.searchContent(value , boardId , where , pageable);
        }else if(where.equals("memberId")){
            result = commentRepository.searchMemberId(value , boardId , where , pageable);
        }
        return result;
    }

}
