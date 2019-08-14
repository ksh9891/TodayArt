package com.artfactory.project01.todayart.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "file")
@DynamicUpdate
@DynamicInsert
public class File {
    @Id
    @Column(name = "file_id", nullable = false)
    @Getter
    @Setter
    private Integer fileId;

    @Column(name = "file_origin_name")
    @Getter
    @Setter
    private String fileOriginName;

    @Column(name = "file_name")
    @Getter
    @Setter
    private String fileName;

    @Column(name = "file_size")
    @Getter
    @Setter
    private Long fileSize;

    @Column(name = "file_reg_dated", updatable = false, insertable = false)
    @Getter
    @Setter
    private Date fileRegDated;

    @Column(name = "file_upload_ip")
    @Getter
    @Setter
    private String fileUploadIp;

    @Column(name = "member_id")
    @Getter
    @Setter
    private Integer memberId;

    @Column(name = "file_content_type")
    @Getter
    @Setter
    @Nullable
    private String fileContentType;
}
