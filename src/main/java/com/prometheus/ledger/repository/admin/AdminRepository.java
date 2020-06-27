package com.prometheus.ledger.repository.admin;

import com.prometheus.ledger.repository.admin.entity.AdminMemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<AdminMemberDTO, String> {
    @Query(value = "SELECT * FROM prod_admin_member WHERE admin_username = ?1 AND admin_password = ?2", nativeQuery = true)
    List<AdminMemberDTO> findByUsernameAndPassword(String username, String password);
}
