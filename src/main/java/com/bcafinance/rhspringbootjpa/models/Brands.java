package com.bcafinance.rhspringbootjpa.models;

import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 01/12/2022
@Last Modified 01/12/2022 14:40
Version 1.0
*/
@Data
@Entity
@Table(name="MstBrand")

public class Brands {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BrId")
    private Long id;

    @Column(name="BrName", length = 15, nullable = false)
    private String brName;

    @Column(name = "BrFrom", length = 15, nullable = false)
    private String brFrom;
}
