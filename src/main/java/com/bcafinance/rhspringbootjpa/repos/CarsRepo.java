package com.bcafinance.rhspringbootjpa.repos;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 30/11/2022
@Last Modified 30/11/2022 14:28
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.models.Cars;
import com.bcafinance.rhspringbootjpa.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarsRepo extends JpaRepository<Cars, Long> {

    Optional<Cars> findByCarNum(String carNum);

    List<Cars> searchByCarTypeStartsWith(String carType);
    List<Cars> findByCarTypeNotLike(String carType);
    List<Cars> findByCarTypeLike(String carType);


}
