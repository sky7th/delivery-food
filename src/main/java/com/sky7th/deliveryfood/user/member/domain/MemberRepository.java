package com.sky7th.deliveryfood.user.member.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByEmail(String email);
  Boolean existsByEmail(String email);

  @Query("SELECT m FROM Member m LEFT JOIN FETCH m.memberAddresses WHERE m.id = :memberId")
  Optional<Member> findByIdWithMemberAddresses(@Param("memberId") Long id);
}