package com.sky7th.deliveryfood.generic.address.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAddressRepository extends JpaRepository<MemberAddress, Long> {

}
