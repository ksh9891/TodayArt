package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
public class Comments implements Serializable {


    @Id
    @Column(name = "comment_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @JoinColumn(table = "article", name = "article_id")
    @Column(name = "article_id", updatable = false, nullable = false)
    private Integer articleId;

    @JoinColumn(table = "member", name = "member_id")
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

    @Column(name = "is_deleted")
    private Integer isDeleted;

}
