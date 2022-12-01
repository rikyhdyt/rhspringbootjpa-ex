package com.bcafinance.rhspringbootjpa.services;


import com.bcafinance.rhspringbootjpa.handler.FormatValidation;
import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.models.Customers;
import com.bcafinance.rhspringbootjpa.repos.CustomerRepo;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class CustomerService  {

    private CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<Customers> findAllCustomers()
    {
        return customerRepo.findAll();
    }

    public Customers findByIdCustomers(Long id) throws Exception
    {
        return customerRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public Customers findByEmailCustomers(String email) throws Exception
    {

        return customerRepo.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_CUSTOMER_NOT_FOUND));
    }

    public void saveCustomers(Customers customers) throws Exception
    {
        if(customers.getEmail()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(customers.getBirthDate()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(customers.getFirstName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(customers.getPhoneNumber()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        FormatValidation.phoneNumberFormatValidation(customers.getPhoneNumber());
        FormatValidation.emailFormatValidation(customers.getEmail());
        FormatValidation.dateFormatYYYYMMDDValidation(customers.getBirthDate().toString());

        Optional<Customers> custByEmail = customerRepo.findByEmail(customers.getEmail());
        if(custByEmail.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
        }
        customerRepo.save(customers);
    }

    @Transactional
    public void updateCustomerById(Customers c) throws Exception{

        Customers customers = customerRepo.findById(c.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_CUSTOMER_NOT_FOUND));

        customers.setModifiedBy("1");
        customers.setModifiedDate(new Date());
        if(c.getFirstName() != null
                && !Objects.equals(customers.getFirstName(),c.getFirstName())
                && !c.getFirstName().equals(""))
        {
            customers.setFirstName(c.getFirstName());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getMiddleName() != null
                && !Objects.equals(customers.getMiddleName(),c.getMiddleName())
                && !c.getMiddleName().equals(""))
        {
            customers.setMiddleName(c.getMiddleName());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getEmail() != null &&
                c.getEmail().length()>0 &&
                !Objects.equals(customers.getEmail(),c.getEmail()))

        {
            FormatValidation.emailFormatValidation(c.getEmail());

            Optional<Customers> cBeanOptional = customerRepo.findByEmail(c.getEmail());
            if(cBeanOptional.isPresent())//it means if exists
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
            }
            customers.setEmail(c.getEmail());
        }

        if(c.getAddress() != null
                && !Objects.equals(customers.getAddress(),c.getAddress())
                && !c.getAddress().equals(""))
        {
            customers.setAddress(c.getAddress());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getPhoneNumber() != null &&
                c.getPhoneNumber().length()>0 &&
                !Objects.equals(customers.getPhoneNumber(),c.getPhoneNumber())){

            FormatValidation.phoneNumberFormatValidation(c.getPhoneNumber());
            customers.setPhoneNumber(c.getPhoneNumber());
        }
        if(c.getBirthDate() != null &&
                !c.getBirthDate().toString().equals("") &&
                !Objects.equals(customers.getBirthDate().toString(),c.getBirthDate().toString()))
        {
            FormatValidation.dateFormatYYYYMMDDValidation(customers.getBirthDate().toString());
            customers.setBirthDate(c.getBirthDate());
        }
    }

    @Transactional
    public void updateCustomerByIdV2(Customers c) throws Exception{

        Customers customers = customerRepo.findById(c.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_CUSTOMER_NOT_FOUND));

        customers.setModifiedBy("1");
        customers.setModifiedDate(new Date());

        if(c.getFirstName() != null
                && !Objects.equals(customers.getFirstName(),c.getFirstName())
                && !c.getFirstName().equals(""))
        {
            customers.setFirstName(c.getFirstName());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getMiddleName() != null
                && !Objects.equals(customers.getMiddleName(),c.getMiddleName())
                && !c.getMiddleName().equals(""))
        {
            customers.setMiddleName(c.getMiddleName());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getEmail() != null &&
                c.getEmail().length()>0 &&
                !Objects.equals(customers.getEmail(),c.getEmail()))

        {
            FormatValidation.emailFormatValidation(c.getEmail());

            Optional<Customers> cBeanOptional = customerRepo.findByEmail(c.getEmail());
            if(cBeanOptional.isPresent())//it means if exists
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXIST);
            }
            customers.setEmail(c.getEmail());
        }

        if(c.getAddress() != null
                && !Objects.equals(customers.getAddress(),c.getAddress())
                && !c.getAddress().equals(""))
        {
            customers.setAddress(c.getAddress());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getPhoneNumber() != null &&
                c.getPhoneNumber().length()>0 &&
                !Objects.equals(customers.getPhoneNumber(),c.getPhoneNumber())){

            FormatValidation.phoneNumberFormatValidation(c.getPhoneNumber());
            customers.setPhoneNumber(c.getPhoneNumber());
        }
        if(c.getBirthDate() != null &&
                !c.getBirthDate().toString().equals("") &&
                !Objects.equals(customers.getBirthDate().toString(),c.getBirthDate().toString()))
        {
            FormatValidation.dateFormatYYYYMMDDValidation(customers.getBirthDate().toString());
            customers.setBirthDate(c.getBirthDate());
        }
    }
}