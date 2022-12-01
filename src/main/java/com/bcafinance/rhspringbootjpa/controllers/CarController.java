package com.bcafinance.rhspringbootjpa.controllers;


import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.handler.ResponseHandler;
import com.bcafinance.rhspringbootjpa.models.Cars;
import com.bcafinance.rhspringbootjpa.models.Customers;
import com.bcafinance.rhspringbootjpa.services.CarsService;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 30/11/2022
@Last Modified 30/11/2022 23:21
Version 1.0
*/

@RestController
@RequestMapping("api/")
public class CarController {

    @Getter
    private CarsService carsService;

    public CarController() {

    }

    @Autowired
    public CarController(CarsService carsService) {
        this.carsService = carsService;
    }

    @PostMapping("/cars")
    public ResponseEntity<Object>
    saveCars(@RequestBody Cars cars) throws Exception {
        if (cars == null) throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        carsService.saveCars(cars);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED, null, null, null);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Object> getCarsById(@PathVariable("id") long id) throws Exception {
        Cars cars = carsService.findByIdCars(id);

        if (cars != null) {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, cars, null, null);

        } else {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/cars/datas/all")
    public ResponseEntity<Object> findAllCars() throws Exception {

        int data = 0;
        List<Cars> lsCars = carsService.findAllCars();

        if (lsCars.size() == 0) {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, lsCars, null, null);
    }

    @PutMapping("/cars/updt")
    public ResponseEntity<Object> updateCarsByID(@RequestBody Cars cars)throws Exception{
        carsService.updateCarsById(cars);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

    @GetMapping("/cars/cartype/{carType}")
    public ResponseEntity<Object> getCarsStartWith(@PathVariable("carType") String carType)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,carsService.findCarsStartWiths(carType),null,null);
    }

    @GetMapping("/cars/cartype/notlike/{carType}")
    public ResponseEntity<Object> getCarsNotLike(@PathVariable("carType") String carType)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,carsService.findCarTypeNotLikes(carType),null,null);
    }


    @GetMapping("/cars/like/{carType}")
    public ResponseEntity<Object> getCarsLike(@PathVariable("carType") String carType)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,carsService.findCarTypeLikes(carType),null,null);
    }


}
