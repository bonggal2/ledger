package com.prometheus.ledger.repository.admin.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "prod_admin_member")
public class AdminMemberDTO {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column(name="admin_id")
    private String adminId;

    @Column(name="admin_password")
    private String adminPassword;

    @Column(name="admin_username")
    private String adminUserame;

    @Column(name="admin_fullname")
    private String adminFullname;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminUserame() {
        return adminUserame;
    }

    public void setAdminUserame(String adminUserame) {
        this.adminUserame = adminUserame;
    }

    public String getAdminFullname() {
        return adminFullname;
    }

    public void setAdminFullname(String adminFullname) {
        this.adminFullname = adminFullname;
    }
}
