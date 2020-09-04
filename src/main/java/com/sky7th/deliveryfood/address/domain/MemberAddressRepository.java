package com.sky7th.deliveryfood.address.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAddressRepository extends JpaRepository<MemberAddress, Long> {

  List<MemberAddress> findAllByMemberId(Long memberId);
}
