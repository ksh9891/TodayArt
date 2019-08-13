package com.artfactory.project01.todayart.model;

import com.artfactory.project01.todayart.entity.Comments;

import java.io.Serializable;
import java.util.Date;

public class CommentsForm implements Serializable {

    private Integer commentId;
    private Integer articleId;
    private Integer memberId;
    private String comment;
    private Date createDated;
    private Date updateDated;
    private Date deleteDated;
    private Integer isDelete;
    private Comments comments;

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
//    private Integer commentId;
//    private Integer ArticleId;
//    private Integer memberId;
//    private String comment;
//    private Date createDated;
//    private Date updateDated;
//    private Date deleteDated;
//    private Integer isDelete;


    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        if(commentId != null){
            comments.setCommentId(this.commentId);
        }else if(articleId != null){
            comments.setArticleId(this.articleId);
        }else if(memberId != null){
            comments.setMemberId(this.memberId);
        }else if(comment != null){
            comments.setComment(this.comment);
        }else if(createDated != null){
            comments.setCreateDated(this.createDated);
        }else if(updateDated != null){
            comments.setUpdateDated(this.updateDated);
        }else if(deleteDated != null){
            comments.setDeleteDated(this.deleteDated);
        }else if(isDelete != null){
            comments.setIsDelete(this.isDelete);
        }
        this.comments=comments;
    }


}
