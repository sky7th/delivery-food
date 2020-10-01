package com.sky7th.deliveryfood.address.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

  Optional<Address> findByBuildingManagementNumber(String buildingManagementNumber);
}
