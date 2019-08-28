package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Article;
import com.artfactory.project01.todayart.entity.BoardCategory;
import com.artfactory.project01.todayart.entity.Member;

import java.io.Serializable;
import java.util.Date;

public class ArticleForm implements Serializable {

    private Integer article_id;
    private BoardCategory boardCategory;
    private Member member_id;
    private Date write_dated;
    private Date update_dated;
    private String title;
    private String content;
    private String is_deleted;
    private Date deleted_dated;
    private String is_hidden;
    private String password;
    private Integer views;
    private Integer product_id;
    private Article article;

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public BoardCategory getBoardCategory() {
        return boardCategory;
    }

    public void setBoardCategory(BoardCategory boardCategory) {
        this.boardCategory = boardCategory;
    }

    public Member getMember_id() {
        return member_id;
    }

    public void setMember_id(Member member_id) {
        this.member_id = member_id;
    }

    public Date getWrite_dated() {
        return write_dated;
    }

    public void setWrite_dated(Date write_dated) {
        this.write_dated = write_dated;
    }

    public Date getUpdate_dated() {
        return update_dated;
    }

    public void setUpdate_dated(Date update_dated) {
        this.update_dated = update_dated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(String is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getDeleted_dated() {
        return deleted_dated;
    }

    public void setDeleted_dated(Date deleted_dated) {
        this.deleted_dated = deleted_dated;
    }

    public String getIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(String is_hidden) {
        this.is_hidden = is_hidden;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        if (article_id != null) {
            article.setArticleId(this.article_id);
        }
        if (boardCategory != null) {
            article.setBoardCategory(this.boardCategory);
        }
        if (member_id != null) {
            article.setMember(this.member_id);
        }
        if (write_dated != null) {
            article.setWriteDated(this.write_dated);
        }
        if (update_dated != null) {
            article.setUpdateDated(new Date());
        }
        if (title != null) {
            article.setTitle(this.title);
        }
        if (content != null) {
            article.setContent(this.content);
        }
        if (is_deleted != null) {
            article.setIsDeleted(this.is_deleted);
        }
        if (deleted_dated != null) {
            article.setDeletedDated(this.deleted_dated);
        }
        if (is_hidden != null) {
            article.setIsHidden(this.is_hidden);
        }
        if (password != null) {
            article.setPassword(this.password);
        }
        if (views != null) {
            article.setViews(this.views);
        }
        if (product_id != null) {
            article.setProductId(this.product_id);
        }
        this.article = article;
    }
}
