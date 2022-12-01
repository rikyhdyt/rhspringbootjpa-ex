package com.bcafinance.rhspringbootjpa.services;

import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.models.Brands;
import com.bcafinance.rhspringbootjpa.models.Cars;
import com.bcafinance.rhspringbootjpa.repos.BrandRepo;
import com.bcafinance.rhspringbootjpa.repos.CarsRepo;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 01/12/2022
@Last Modified 01/12/2022 15:04
Version 1.0
*/
@Service
@Transactional
public class BrandService {
    private BrandRepo brandRepo;

    public List<Brands> findAllBrands()
    {
        return brandRepo.findAll();
    }

    public Brands findByIdBrand(Long id) throws Exception
    {
        return brandRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    @Autowired
    public BrandService(BrandRepo brandRepo){
        this.brandRepo=brandRepo;
    }

    public void saveBrand(Brands brands) throws Exception{
        if (brands.getBrName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if (brands.getBrFrom()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        brandRepo.save(brands);
    }

}
