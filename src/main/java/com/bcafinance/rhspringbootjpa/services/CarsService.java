package com.bcafinance.rhspringbootjpa.services;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 30/11/2022
@Last Modified 30/11/2022 14:32
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.models.Cars;
import com.bcafinance.rhspringbootjpa.models.Customers;
import com.bcafinance.rhspringbootjpa.repos.CarsRepo;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CarsService {

    private CarsRepo carsRepo;

    @Autowired
    public CarsService(CarsRepo carsRepo){
        this.carsRepo=carsRepo;
    }

    public List<Cars> findAllCars()
    {
        return carsRepo.findAll();
    }

    public Cars findByIdCars(Long id) throws Exception
    {
        return carsRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public void saveCars(Cars cars) throws Exception{
        if (cars.getCarNum()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if (cars.getCarType()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if (cars.getCarBrand()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if (cars.getCarColor()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if (cars.getCarYear()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        Optional<Cars>carByNum = carsRepo.findByCarNum(cars.getCarNum());
        if (carByNum.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NUM_EXIST);
        }
        carsRepo.save(cars);
    }

    @Transactional
    public void updateCarsById(Cars c) throws Exception{
        Cars cars = carsRepo.findById(c.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_CAR_NOT_FOUND));
        cars.setModifiedBy("1");
        cars.setModifiedDate(new Date());

        if (c.getCarNum() != null &&
        c.getCarNum().length()>0 &&
        !Objects.equals(cars.getCarNum(),c.getCarNum())) {
            Optional<Cars> cBeanOptional = carsRepo.findByCarNum(c.getCarNum());
            if (cBeanOptional.isPresent())//it means if exists
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_NUM_EXIST);
            }
            cars.setCarNum(c.getCarNum());
        }

        if(c.getCarType() != null
                && !Objects.equals(cars.getCarType(),c.getCarType())
                && !c.getCarType().equals(""))
        {
            cars.setCarType(c.getCarType());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getCarBrand() != null
                && !Objects.equals(cars.getCarBrand(),c.getCarBrand())
                && !c.getCarBrand().equals(""))
        {
            cars.setCarBrand(c.getCarBrand());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getCarColor() != null
                && !Objects.equals(cars.getCarColor(),c.getCarColor())
                && !c.getCarColor().equals(""))
        {
            cars.setCarColor(c.getCarColor());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getCarYear() != null
                && !Objects.equals(cars.getCarYear(),c.getCarYear())
                && !c.getCarYear().equals(""))
        {
            cars.setCarYear(c.getCarYear());//BERARTI ADA PERUBAHAN DI SINI
        }
    }

    public List<Cars> findCarsStartWiths(String carType)
    {
        return carsRepo.searchByCarTypeStartsWith(carType);
    }

    public List<Cars> findCarTypeNotLikes(String carType)
    {
        return carsRepo.findByCarTypeNotLike(carType);
    }

    public List<Cars> findCarTypeLikes(String carType)
    {
        return carsRepo.findByCarTypeLike(carType);
    }




}
