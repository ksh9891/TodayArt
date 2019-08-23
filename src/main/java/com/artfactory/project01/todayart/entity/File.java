package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "file")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class File {
    @Id
    @Column(name = "file_id", updatable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileId;

    @Column(name = "file_origin_name")
    private String fileOriginName;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_reg_dated", updatable = false, insertable = false)
    private Date fileRegDated;

    @Column(name = "file_upload_ip")
    private String fileUploadIp;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "file_content_type")
    @Nullable
    private String fileContentType;
}
