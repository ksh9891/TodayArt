package com.artfactory.project01.todayart.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comments")
@DynamicInsert
@DynamicUpdate
public class Comments implements Serializable {


    @Id
    @Column(name = "comment_id", updatable = false, nullable = false)
    private Integer commentId;

    @Column(name = "article_id", updatable = false, nullable = false)
    private Integer articleId;

    @Column(name = "member_id", updatable = false, nullable = false)
    private Integer memberId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "create_dated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDated;

    @Column(name = "update_dated")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updateDated;

    @Column(name = "delete_dated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteDated;

    @Column(name = "is_delete")
    private Integer isDelete;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDated() {
        return createDated;
    }

    public void setCreateDated(Date createDated) {
        this.createDated = createDated;
    }

    public Date getUpdateDated() {
        return updateDated;
    }

    public void setUpdateDated(Date updateDated) {
        this.updateDated = updateDated;
    }

    public Date getDeleteDated() {
        return deleteDated;
    }

    public void setDeleteDated(Date deleteDated) {
        this.deleteDated = deleteDated;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
