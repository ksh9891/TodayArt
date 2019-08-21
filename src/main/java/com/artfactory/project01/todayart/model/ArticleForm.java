package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Article;
import com.artfactory.project01.todayart.entity.BoardCategory;
import com.artfactory.project01.todayart.entity.Member;


import java.io.Serializable;
import java.util.Date;

public class ArticleForm implements Serializable {

    private Integer article_id;
    private BoardCategory board_id;
    private Member member_id;
    private Date write_dated;
    private Date update_dated;
    private String title;
    private String content;
    private Integer group;
    private Integer depth;
    private Integer reply_order;
    private Integer is_deleted;
    private Date deleted_dated;
    private Integer is_hidden;
    private String password;
    private Integer is_reply;
    private Integer views;
    private Integer product_id;
    private Article article;

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public BoardCategory getBoard_id() {
        return board_id;
    }

    public void setBoard_id(BoardCategory board_id) {
        this.board_id = board_id;
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

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getReply_order() {
        return reply_order;
    }

    public void setReply_order(Integer reply_order) {
        this.reply_order = reply_order;
    }

    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getDeleted_dated() {
        return deleted_dated;
    }

    public void setDeleted_dated(Date deleted_dated) {
        this.deleted_dated = deleted_dated;
    }

    public Integer getIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(Integer is_hidden) {
        this.is_hidden = is_hidden;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIs_reply() {
        return is_reply;
    }

    public void setIs_reply(Integer is_reply) {
        this.is_reply = is_reply;
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
        if(article_id != null){
            article.setArticleId(this.article_id);
        }
        if(board_id != null){
            article.setBoardCategory(this.board_id);
        }
        if(member_id != null){
            article.setMember(this.member_id);
        }
        if(write_dated != null){
            article.setWriteDated(this.write_dated);
        }
        if(update_dated != null){
            article.setUpdateDated(new Date());
        }
        if(title != null){
            article.setTitle(this.title);
        }
        if(content != null){
            article.setContent(this.content);
        }
        if(group != null){
            article.setGroup(this.group);
        }
        if(depth != null){
            article.setDepth(this.depth);
        }
        if(reply_order != null){
            article.setReplyOrder(this.reply_order);
        }
        if(is_deleted != null){
            article.setIsDeleted(this.is_deleted);
        }
        if(deleted_dated != null){
            article.setDeletedDated(this.deleted_dated);
        }
        if(is_hidden != null){
            article.setIsHidden(this.is_hidden);
        }
        if(password != null){
            article.setPassword(this.password);
        }
        if(is_reply != null){
            article.setIsReply(this.is_reply);
        }
        if(views != null){
            article.setViews(this.views);
        }
        if(product_id != null){
            article.setProductId(this.product_id);
        }
        this.article = article;
    }
}
