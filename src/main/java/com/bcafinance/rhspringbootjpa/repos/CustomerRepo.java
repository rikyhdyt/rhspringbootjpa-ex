package com.bcafinance.rhspringbootjpa.repos;

import com.bcafinance.rhspringbootjpa.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepo extends JpaRepository<Customers,Long> {

    Optional<Customers> findByEmail(String email);
}
