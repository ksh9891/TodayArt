package com.artfactory.project01.todayart.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "privilege")
public class Privilege implements GrantedAuthority, Serializable {
    @Id
    @Column(name = "p_id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "desc")
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
