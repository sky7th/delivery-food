package com.sky7th.deliveryfood.user.owner;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

  Optional<Owner> findByEmail(String email);
}