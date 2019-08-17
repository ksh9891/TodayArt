package com.artfactory.project01.todayart.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "article")
@DynamicInsert
@DynamicUpdate
public class Article implements Serializable {


    @Id
    @Column(columnDefinition = "INT", name = "article_id", updatable = false, nullable = false)
    private Integer articleId;

    @Column(name = "board_id", nullable = false)
    private Integer boardId;

    @Column(name = "member_id", updatable = false, nullable = false)
    private Integer memberId;

    @Column(name = "write_dated", updatable = false, nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date writeDated;

    @Column(name = "update_dated", nullable = true)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDated;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "group")
    private Integer group;

    @Column(name = "depth")
    private Integer depth;

    @Column(name = "reply_order")
    private Integer replyOrder;

    @Column(name = "is_deleted")
    private Integer isDeleted;

    @Column(name = "delete_dated", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDated;

    @Column(name = "is_hidden")
    private Integer isHidden;

    @Column(name = "password")
    private String password;

    @Column(name = "is_reply")
    private Integer isReply;

    @Column(name = "views")
    private Integer views;

    @Column(name = "product_id")
    private Integer productId;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Date getWriteDated() {
        return writeDated;
    }

    public void setWriteDated(Date writeDated) {
        this.writeDated = writeDated;
    }

    public Date getUpdateDated() {
        return updateDated;
    }

    public void setUpdateDated(Date updateDated) {
        this.updateDated = updateDated;
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

    public Integer getReplyOrder() {
        return replyOrder;
    }

    public void setReplyOrder(Integer replyOrder) {
        this.replyOrder = replyOrder;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getDeletedDated() {
        return deletedDated;
    }

    public void setDeletedDated(Date deletedDated) {
        this.deletedDated = deletedDated;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsReply() {
        return isReply;
    }

    public void setIsReply(Integer isReply) {
        this.isReply = isReply;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void updateViews(Integer views) {
        this.views = views + 1;
    }
}
