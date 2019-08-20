package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments,Integer> {

    //게시물 아이디에 해당하는 댓글 목록 가져오기
    @Query(value = "SELECT * FROM comments WHERE article_id = ?1 AND is_deleted = 0 ORDER BY create_dated ASC ", nativeQuery = true)
    Page<Comments> findByArticleId(Integer articleId , Pageable pageable);

    //게시글에 해당하는 댓글수 출력
    @Query(value = "SELECT COUNT(article_id) FROM comments WHERE article_id = ?1", nativeQuery = true)
    List<Comments> numberOfComments(Integer articleId, Integer count);


    //content검색
    @Query(value = "SELECT * FROM comments WHERE board_id = ?2 AND is_deleted = 0 AND content LIKE %?1% ORDER BY write_dated DESC", nativeQuery = true)
    Page<Comments> searchContent(String value, Integer boardId ,String where, Pageable pageable);

    //memberId검색
    @Query(value = "SELECT * FROM comments WHERE board_id = ?2 AND is_deleted = 0 AND member_id LIKE %?1% ORDER BY write_dated DESC", nativeQuery = true)
    Page<Comments> searchMemberId(String value, Integer boardId ,String where, Pageable pageable);


}
