package com.sky7th.deliveryfood.user.rider.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Long> {

  Optional<Rider> findByEmail(String email);
  Boolean existsByEmail(String email);
}