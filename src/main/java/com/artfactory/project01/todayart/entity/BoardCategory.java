package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "board_category")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class BoardCategory {

    @Id
    @Column(name = "board_id")
    private Integer boardId;

    @Column(name = "board_name")
    private String boardName;
}
