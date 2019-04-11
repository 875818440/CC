package com.CC.CCDemo.Demo;

import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="area")
@EntityListeners(AuditingEntityListener.class)
public class Area implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String areaName;
    @Column
    private Integer status;
    @Column
    @CreatedDate
    private Date cteateDate;
    @Column
    @LastModifiedDate
    private Date updateDate;
    @ManyToMany
    @JoinTable(name = "user_area",
            joinColumns = @JoinColumn(name = "area_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users =new HashSet<>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
