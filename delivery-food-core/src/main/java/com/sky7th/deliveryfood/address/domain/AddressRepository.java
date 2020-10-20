package com.sky7th.deliveryfood.address.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

  Optional<Address> findByBuildingManagementNumber(String buildingManagementNumber);

  @Query(value = "SELECT * FROM address a WHERE a.town_name LIKE CONCAT(:townName, '%')"
               + "AND a.building_number LIKE CONCAT(:buildingNumber, '%')"
               + "AND a.building_side_number LIKE CONCAT(:buildingSideNumber, '%')"
               + "AND a.building_management_number > '' ORDER BY a.building_management_number LIMIT 10", nativeQuery = true)
  List<Address> findByTownName(String townName, String buildingNumber, String buildingSideNumber);

  @Query(value = "SELECT * FROM address a WHERE a.road_name LIKE CONCAT(:roadName, '%')"
      + "AND a.building_number LIKE CONCAT(:buildingNumber, '%')"
      + "AND a.building_side_number LIKE CONCAT(:buildingSideNumber, '%')"
      + "AND a.building_management_number > '' ORDER BY a.building_management_number LIMIT 10", nativeQuery = true)
  List<Address> findByRoadName(String roadName, String buildingNumber, String buildingSideNumber);

  @Query(value = "SELECT * FROM address a WHERE a.building_name_for_country LIKE CONCAT(:buildingName, '%')"
      + "AND a.building_management_number > '' ORDER BY a.building_management_number LIMIT 10", nativeQuery = true)
  List<Address> findByBuildingName(String buildingName);
}
