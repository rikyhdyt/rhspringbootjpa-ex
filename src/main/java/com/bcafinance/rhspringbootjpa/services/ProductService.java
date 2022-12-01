package com.bcafinance.rhspringbootjpa.services;

import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.models.Products;
import com.bcafinance.rhspringbootjpa.repos.ProductRepo;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ProductService {

    @Getter
    private ProductRepo productRepo;

    @Autowired
    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public void saveProduct(Products products) throws Exception{
        if(products.getDescription()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(products.getName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        productRepo.save(products);
    }
    public Products findByIdProduct(Long id) throws Exception
    {
//        int intK = 5/0;
        return productRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_PRODUCT_NOT_FOUND));
    }
    public Iterable<Products> findAllProducts()throws Exception{
        return productRepo.findAll();
    }

    public void deleteByIdProduct(Long id) throws Exception{
        productRepo.deleteById(id);
    }

    public Products findByNameProduct(String name) throws Exception
    {
        List<Products> lsTest = productRepo.searchByNameStartsWith(name);
        for(int i=0;i<lsTest.size();i++)
        {
            System.out.println(lsTest.get(i).getName());
            System.out.println(lsTest.get(i).getId());
            System.out.println(lsTest.get(i).getPrice());
            System.out.println(lsTest.get(i).getDescription());
            System.out.println();

        }
        return productRepo.findProductByName(name).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_PRODUCT_NOT_FOUND));
    }

    @Transactional
    public void updateProducts(Products p) throws Exception{

        Products products = productRepo.findById(p.getId()).orElseThrow(()->
                    new ResourceNotFoundException(ConstantMessage.WARNING_PRODUCT_NOT_FOUND));

        products.setModifiedBy("1");
        products.setModifiedDate(new Date());
        if(p.getName() != null && !Objects.equals(products.getName(),p.getName()) && !p.getName().equals(""))
        {
            products.setName(p.getName());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(p.getDescription() != null && !Objects.equals(products.getDescription(),p.getDescription()) && !p.getDescription().equals(""))
        {
            products.setDescription(p.getDescription());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(!(p.getPrice()<=((1.0/2) * products.getPrice())) && p.getPrice()!=0 && !(p.getPrice()>(3*products.getPrice())))
        {
            products.setPrice(p.getPrice());
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_PRODUCT_PRICE_SOP);
        }
    }


}
