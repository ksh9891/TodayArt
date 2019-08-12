package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.ArticleVO;
import java.io.Serializable;
import java.util.Date;

public class ArticleForm implements Serializable {

    private Integer article_id;
    private Integer board_id;
    private Integer member_id;
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
    private ArticleVO articleVO;

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Integer getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Integer board_id) {
        this.board_id = board_id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
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

    public ArticleVO getArticleVO() {
        return articleVO;
    }

    public void setArticleVO(ArticleVO articleVO) {
        if(article_id != null){
            articleVO.setArticle_id(this.article_id);
        }else if(board_id != null){
            articleVO.setBoard_id(this.board_id);
        }else if(member_id != null){
            articleVO.setMember_id(this.member_id);
        }else if(write_dated != null){
            articleVO.setWrite_dated(this.write_dated);
        }else if(update_dated != null){
            articleVO.setUpdate_dated(new Date());
        }else if(title != null){
            articleVO.setTitle(this.title);
        }else if(content != null){
            articleVO.setContent(this.content);
        }else if(group != null){
            articleVO.setGroup(this.group);
        }else if(depth != null){
            articleVO.setDepth(this.depth);
        }else if(reply_order != null){
            articleVO.setReply_order(this.reply_order);
        }else if(is_deleted != null){
            articleVO.setIs_deleted(this.is_deleted);
        }else if(deleted_dated != null){
            articleVO.setDeleted_dated(this.deleted_dated);
        }else if(is_hidden != null){
            articleVO.setIs_hidden(this.is_hidden);
        }else if(password != null){
            articleVO.setPassword(this.password);
        }else if(is_reply != null){
            articleVO.setIs_reply(this.is_reply);
        }else if(views != null){
            articleVO.setViews(this.views);
        }else if(product_id != null){
            articleVO.setProduct_id(this.product_id);
        }
        this.articleVO = articleVO;
    }
}
