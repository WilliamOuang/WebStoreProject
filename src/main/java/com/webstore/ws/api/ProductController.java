/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webstore.ws.api;

import com.webstore.model.Product;
import com.webstore.services.ProductDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author WeiliangOuyang
 */
@RestController
public class ProductController {
    
    @Autowired
     ProductDao _productDao;

    public static List<Product> getProducts() {
        return null;
    }
   
    @RequestMapping(value = "/api/products",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Product>> getAllProduct() {

        return new ResponseEntity<Collection<Product>>(_productDao.getAllProduct(), HttpStatus.OK);
    }
    
    Product findProduct(int id){
        return _productDao.getById(id);
    }
    
    
    @RequestMapping(value = "/api/products/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable int id) {

        final Product p =_productDao.getById(id);
        if (p == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(p, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/products",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@RequestBody  Product p) {
     _productDao.save(p);
        return new ResponseEntity<Product>(p, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/api/products",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@RequestBody  Product p) {

            _productDao.update(p);
            
        return new ResponseEntity<Product>(p, HttpStatus.CREATED);
    }
    
    
    @RequestMapping(value = "/api/products/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {

        Product pro =findProduct(id);
        _productDao.delete(pro);

        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
    
}
