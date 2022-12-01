package com.bcafinance.rhspringbootjpa.controllers;

import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.handler.ResponseHandler;
import com.bcafinance.rhspringbootjpa.models.Products;
import com.bcafinance.rhspringbootjpa.services.ProductService;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class ProductController {


    @Getter
    private ProductService productService;

    public ProductController() {
    }


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") long id) throws Exception {
            Products products = productService.findByIdProduct(id);

            if(products != null)
            {
                return new ResponseHandler().
                        generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,products,null,null);
            }
            else
            {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
            }
    }

    @PostMapping("/products")
    public ResponseEntity<Object>
    saveProduct(@RequestBody Products products,
                @RequestHeader Map<String,String> headers,
                @RequestParam Map<String,String> params,
                WebRequest request) throws Exception {

        String[] attributeNames = request.getAttributeNames(0);
        for (String a:
             attributeNames) {
            System.out.println("ATTR 1 => "+a);
        }
        System.out.println("SESSION ID -> "+request.getSessionId());
//        System.out.println();
//        System.out.println(request.getAttributeNames(1));
//        System.out.println(request.getAttributeNames(2));
//        System.out.println(request.getAttributeNames(3));
//        for (String a : request.getAttributeNames()
//             ) {
//            System.out.println("PARAM VALUE => "+a);
//        }
//        Object obj = request.getAttribute("org.springframework.web.util.UrlPathHelper.PATH", 0);
//        Object objOne = request.getAttribute("session", 1);
//        String uri = String.valueOf(obj);
//        String session = String.valueOf(objOne);
//        System.out.println("URI ====> "+uri);
//        System.out.println("Session ====> "+session);
//        System.out.println(headers);
//        System.out.println(params);

//        params.forEach((k,v)->{
//            System.out.println("Key : "+k+" Value : "+v);
//        });
//        System.out.println("============================================================================================");
//        headers.forEach((k,v)->{
//            System.out.println("Key : "+k+" Value : "+v);
//        });

        if(products==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);

        productService.saveProduct(products);
//        int intK = 5/0;//buat di errorken
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/products/datas/all/0")
    public ResponseEntity<Object> findAllProducts()throws Exception{

        int data = 0;
        Iterable<Products> lsProducts = productService.findAllProducts();

        if(lsProducts instanceof Collection<Products>)
        {
            data = ((Collection<Products>) lsProducts).size();
        }
        if(data==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsProducts,null,null);
    }

    @GetMapping("/products/datas/search/{name}")
    public ResponseEntity<Object> getProductByName(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,productService.findByNameProduct(name),null,null);
    }

    @PutMapping("/products/u")
    public ResponseEntity<Object> updateProductByID(@RequestBody Products products)throws Exception{
        productService.updateProducts(products);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }
}
