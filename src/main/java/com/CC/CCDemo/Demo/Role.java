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
@Table(name="role")
@EntityListeners(AuditingEntityListener.class)
public class Role implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique=true)
    private String roleName;
    @Column
    @CreatedDate
    private Date createDate;
    @Column
    @LastModifiedDate
    private Date updateDate;
    @ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Role> users= new HashSet<>();

    public Set<Role> getUsers() {
        return users;
    }

    public void setUsers(Set<Role> users) {
        this.users = users;
    }

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
