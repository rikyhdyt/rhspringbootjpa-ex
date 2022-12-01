package com.bcafinance.rhspringbootjpa.controllers;

import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.handler.ResponseHandler;
import com.bcafinance.rhspringbootjpa.models.Customers;
import com.bcafinance.rhspringbootjpa.services.CustomerService;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CustomerController {

    @Getter
    private CustomerService customerService;


    public CustomerController() {
    }

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/v1/customers")
    public ResponseEntity<Object>
    saveCustomer(@RequestBody Customers customers) throws Exception {

        if(customers==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        customerService.saveCustomers(customers);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/customers/{id}")
    public ResponseEntity<Object> getCutomersById(@PathVariable("id") long id) throws Exception {
        Customers customers = customerService.findByIdCustomers(id);

        if(customers != null)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,customers,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/v1/customers/datas/all/0")
    public ResponseEntity<Object> findAllCustomers()throws Exception{

        int data = 0;
        List<Customers> lsCustomers = customerService.findAllCustomers();

        if(lsCustomers.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsCustomers,null,null);
    }

    @GetMapping("/v1/customers/datas/search/{email}")
    public ResponseEntity<Object> getCustomerByEmail(@PathVariable("email") String email)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,customerService.findByEmailCustomers(email),null,null);
    }

    @PutMapping("/v1/customers/t")
    public ResponseEntity<Object> updateCustomerByID(@RequestBody Customers customers)throws Exception{
        customerService.updateCustomerById(customers);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

    @PutMapping("/v2/customers/t")
    public ResponseEntity<Object> updateCustomerByIDV2(@RequestBody Customers customers)throws Exception{
        customerService.updateCustomerByIdV2(customers);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

}
