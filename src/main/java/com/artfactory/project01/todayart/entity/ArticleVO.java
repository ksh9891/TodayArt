package com.artfactory.project01.todayart.entity;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "article")
@DynamicInsert
public class ArticleVO implements Serializable {

//    `article_id` int not null, `board_id` int not null, `writter_id` int not null, `write_dated` datetime not null default now(),
//	`update_dated` datetime null, `title` varchar(255) not null, `content` text not null, `group` int null, `depth` int not null default 0,
//   `reply_order` int null,`is_deleted` int not null default 0 comment "0:미삭제, 1:삭제", `delete_dated` datetime null,
//    `is_hidden`	int not null default 0 comment "0:공개글, 1:비밀글", `password` varchar(255) null, `is_reply` int not null default 0 comment "0:미답변, 1:답변",
//   `views` int not null default 0, `product_id` int null

    @Id
    @Column(columnDefinition = "INT", name = "article_id", updatable = false, nullable = false)
    private int article_id;

    @Column(name = "board_id", nullable = false)
    private int board_id;


    @Column(name = "writter_id", updatable = false, nullable = false)
    private int writter_id;

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
    private int group;

    @Column(name = "depth")
    private int depth;

    @Column(name = "reply_order")
    private int reply_order;

    @Column(name = "is_deleted")
    private int is_deleted;

    @Column(name = "delete_dated")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted_dated;

    @Column(name = "is_hidden")
    private int is_hidden;

    @Column(name = "password")
    private String password;

    @Column(name = "is_reply")
    private int is_reply;

    @Column(name = "views")
    private int views;

    @Column(name = "product_id")
    private int product_id;

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public int getWritter_id() {
        return writter_id;
    }

    public void setWritter_id(int writter_id) {
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

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getReply_order() {
        return reply_order;
    }

    public void setReply_order(int reply_order) {
        this.reply_order = reply_order;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getDeleted_dated() {
        return deleted_dated;
    }

    public void setDeleted_dated(Date deleted_dated) {
        this.deleted_dated = deleted_dated;
    }

    public int getIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(int is_hidden) {
        this.is_hidden = is_hidden;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIs_reply() {
        return is_reply;
    }

    public void setIs_reply(int is_reply) {
        this.is_reply = is_reply;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
