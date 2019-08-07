package com.artfactory.project01.todayart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="role")
public class Role implements Serializable {
    @Id
    @Column(name="r_id")
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="desc")
    private String desc;

    @JsonIgnore
    @ManyToMany(mappedBy="roles")
    private Set<Member> memebers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_privilege",
            joinColumns = @JoinColumn(name="r_id"),
            inverseJoinColumns = @JoinColumn(name = "p_id")
    )
    private Set<Privilege> privileges;


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

    public Set<Member> getMemebers() {
        return memebers;
    }

    public void setMemebers(Set<Member> memebers) {
        this.memebers = memebers;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }
}
