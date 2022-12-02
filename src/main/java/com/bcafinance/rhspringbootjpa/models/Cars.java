package com.bcafinance.rhspringbootjpa.models;
/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 30/11/2022
@Last Modified 30/11/2022 14:13
Version 1.0
*/

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name= "MstCars")

public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarsId")
    private Long id;

    @Column(name = "CarNum",length = 15 ,nullable = false,unique = true)
    private String carNum;

    @Column(name = "CarType",length = 50 ,nullable = false)
    private String carType;

    @ManyToOne
//    @JoinColumn(name = "CarBrand")
    private Brands carBrand;

    @Column(name = "CarColor",length = 50 ,nullable = false)
    private String carColor;

    @Column(name = "CarYear",length = 4 ,nullable = false)
    private Integer carYear;

    @Column(name = "CreatedBy",nullable = true)
    private String createdBy = "1";

    //    @Column(name = "CreatedDate",nullable = true, columnDefinition = "datetime2(7) DEFAULT GETDATE() ")
    @Column(name = "CreatedDate",nullable = true)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = true)
    private boolean isActive = true;

    public Cars(){

    }


}
