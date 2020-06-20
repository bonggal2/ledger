package com.prometheus.ledger.repository.member;

import com.prometheus.ledger.repository.member.entity.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberDTO, String> {

    @Query(value = "SELECT member_id FROM prod_member WHERE username = ?1 AND password = ?2", nativeQuery = true)
    List<MemberDTO> findByUsernameAndPassword(String username, String password);
}
