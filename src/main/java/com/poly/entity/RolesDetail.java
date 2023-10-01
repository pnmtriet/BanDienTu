package com.poly.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles_detail")
public class RolesDetail extends BaseEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "account_id", nullable = true)
    private Integer accountId;
    @Basic
    @Column(name = "role_id", nullable = true)
    private Integer roleId;

    @Column(name = "is_deleted", nullable = true)
    private Boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Account accountByAccountId;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Roles rolesByRoleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Account getAccountByAccountId() {
        return accountByAccountId;
    }

    public void setAccountByAccountId(Account accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
    }

    public Roles getRolesByRoleId() {
        return rolesByRoleId;
    }

    public void setRolesByRoleId(Roles rolesByRoleId) {
        this.rolesByRoleId = rolesByRoleId;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
