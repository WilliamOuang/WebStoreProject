/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webstore.ws.api;

import com.webstore.model.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    
    private static List<Product> products= new ArrayList<Product>();

    public static void setProducts(List<Product> products) {
        ProductController.products = products;
    }

    public static List<Product> getProducts() {
        return products;
    }

    static{
          products.add(new Product(1,"Product1",20.00));
          products.add(new Product(2,"Product2",10.00));
    }
      
    
    @RequestMapping(value = "/api/products",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Product>> getAllProduct() {

        return new ResponseEntity<Collection<Product>>(products, HttpStatus.OK);
    }
    
    Product findProduct(int id){
        
        for( int i = 0; i <products.size();i++ ){
            Product p = products.get(i);
             if(p.getProductId()==id){
        
                return p;
            }
        }
       
        return null;
    }
    
    
    @RequestMapping(value = "/api/products/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable final int id) {

        final Product p =findProduct(id);
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
            p.setProductId(products.size()+1);
            products.add(p);

        return new ResponseEntity<Product>(p, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/api/products",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@RequestBody  Product p) {

            
            Product pro =findProduct(p.getProductId());
            products.remove(pro);
            products.add(p);
            
        return new ResponseEntity<Product>(p, HttpStatus.CREATED);
    }
    
    
    @RequestMapping(value = "/api/products/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {

        Product pro =findProduct(id);
        products.remove(pro);

        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
    
}
