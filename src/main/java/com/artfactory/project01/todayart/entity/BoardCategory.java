package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Entity
@Table(name = "board_category")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
public class BoardCategory implements Serializable {

    @Id
    @Column(name = "board_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardId;

    @Column(name = "board_name")
    private String boardName;

}
