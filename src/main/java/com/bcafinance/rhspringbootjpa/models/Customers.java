package com.bcafinance.rhspringbootjpa.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Data
@Entity
@Table(name = "MstCustomers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private Long id;

    @Column(name = "Email",length = 50 ,nullable = false,unique = true)
    private String email;

    @Column(name = "PhoneNumber", length = 16, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "FirstName",length = 20,nullable = false)
    private String firstName;

    @Column(name = "MiddleName",length = 20,nullable = true)
    private String middleName;

    @Column(name = "LastName",length = 20,nullable = true)
    private String lastName;

    @Column(name = "Address", nullable = true)
    private String address;

    @Column(name = "BirthDate",nullable = false)
    private LocalDate birthDate;

    @Transient//tidak akan menggenerate kolom di tabel
    private short age;//Menggunakan Object Integer karena nilai return dari Period.between adalah Integer

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    //    @Column(name = "CreatedDate",nullable = true, columnDefinition = "datetime2(7) DEFAULT GETDATE() ")
    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

    public Customers() {
    }

    public short getAge() {
        return (short) Period.between(this.birthDate,LocalDate.now()).getYears();//dikonversi ke short dari Integer
    }
}
