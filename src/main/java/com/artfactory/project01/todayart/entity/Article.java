package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@Getter
@Setter
public class Article implements Serializable {


    @Id
    @Column(columnDefinition = "INT", name = "article_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;

    @JoinColumn(table="board_category", name = "board_id")
    @Column(name="board_id")
    private Integer boardId;
//
//    @ManyToOne(fetch=FetchType.EAGER, targetEntity = BoardCategory.class)
//    @JoinColumn(name = "borad_id")
//    private BoardCategory boardCategory;


    @JoinColumn(table = "member", name = "member_id")
    @Column(name = "member_id")
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

}
