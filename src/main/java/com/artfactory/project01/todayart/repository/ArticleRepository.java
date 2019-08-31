package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.Article;
import com.artfactory.project01.todayart.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.DiscriminatorColumn;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {

    //게시판 아이디가 ?인게시물만 가져오기(게시글에 달려있는
   @Query(value = "SELECT a.* ,(SELECT ifnull(COUNT(*),0) FROM comments WHERE article_id = a.article_id) as cnt\n" +
           "FROM article a \n" +
           "WHERE a.board_id = ?1\n" +
           "AND a.is_deleted = 'n'  \n" +
           "ORDER BY a.write_dated DESC ", nativeQuery = true)
   Page<Article> findByBoardId(Integer boardId , Pageable pageable);

   //title 검색
   @Query(value = "SELECT * FROM article WHERE board_id = ?2 AND is_deleted = 'n' AND title LIKE %?1% ORDER BY write_dated DESC", nativeQuery = true)
   Page<Article> searchTitle(String value, Integer boardId ,String where, Pageable pageable);

   //content검색
    @Query(value = "SELECT * FROM article WHERE board_id = ?2 AND is_deleted = 'n' AND content LIKE %?1% ORDER BY write_dated DESC", nativeQuery = true)
    Page<Article> searchContent(String value, Integer boardId ,String where, Pageable pageable);

   //memberId검색
    @Query(value = "SELECT * FROM article WHERE board_id = ?2 AND is_deleted = 'n' AND member_id LIKE %?1% ORDER BY write_dated DESC", nativeQuery = true)
    Page<Article> searchMemberId(String value, Integer boardId ,String where, Pageable pageable);

    //title+content검색
    @Query(value = "SELECT * FROM article WHERE board_id = ?2 AND is_deleted = 'n' AND title LIKE %?1% " +
            "OR content LIKE %?1% ORDER BY write_dated DESC", nativeQuery = true)
    Page<Article> searchTitleContent(String value, Integer boardId ,String where, Pageable pageable);

    //게시글에 해당하는 댓글수 출력
    @Query(value = "SELECT COUNT(article_id) FROM comments WHERE article_id = ?1", nativeQuery = true)
    List<Comments> numberOfComments(Integer articleId);


}
