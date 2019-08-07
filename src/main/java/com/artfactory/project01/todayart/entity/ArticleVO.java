package com.artfactory.project01.todayart.entity;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "article")
@DynamicInsert
@DynamicUpdate
public class ArticleVO implements Serializable {

//    `article_id` int not null, `board_id` int not null, `writter_id` int not null, `write_dated` datetime not null default now(),
//	`update_dated` datetime null, `title` varchar(255) not null, `content` text not null, `group` int null, `depth` int not null default 0,
//   `reply_order` int null,`is_deleted` int not null default 0 comment "0:미삭제, 1:삭제", `delete_dated` datetime null,
//    `is_hidden`	int not null default 0 comment "0:공개글, 1:비밀글", `password` varchar(255) null, `is_reply` int not null default 0 comment "0:미답변, 1:답변",
//   `views` int not null default 0, `product_id` int null

    @Id
    @Column(columnDefinition = "INT", name = "article_id", updatable = false, nullable = false)
    private Integer article_id;

    @Column(name = "board_id", nullable = false)
    private Integer board_id;

    @Column(name = "writter_id", updatable = false, nullable = false)
    private Integer writter_id;

    @Column(name = "write_dated", updatable = false, nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date write_dated;

    @Column(name = "update_dated")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date update_dated;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "group")
    private Integer group;

    @Column(name = "depth")
    private Integer depth;

    @Column(name = "reply_order")
    private Integer reply_order;

    @Column(name = "is_deleted")
    private Integer is_deleted;

    @Column(name = "delete_dated")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_dated;

    @Column(name = "is_hidden")
    private Integer is_hidden;

    @Column(name = "password")
    private String password;

    @Column(name = "is_reply")
    private Integer is_reply;

    @Column(name = "views")
    private Integer views;

    @Column(name = "product_id")
    private Integer product_id;

    public Integer getArticle_id(Integer id) {
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

    public Integer getWritter_id() {
        return writter_id;
    }

    public void setWritter_id(Integer writter_id) {
        this.writter_id = writter_id;
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
}
