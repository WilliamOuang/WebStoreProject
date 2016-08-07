/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webstore.ws.api;

import com.webstore.model.Order;
import com.webstore.model.OrderProduct;
import com.webstore.model.Product;
import com.webstore.services.OrderDao;
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
public class OrderController {
    
    private static    List<Order> orderList= new ArrayList<Order>();
 
    static{
//     Product p =   new Product(1,"dd",2);
//        OrderProduct o = new OrderProduct();
//            o.setP(p);
//            o.setQuantity(1);
//            o.setOrderPrice(p.getPrice());
//            o.setOrderTime(System.currentTimeMillis());
//            
//        OrderProduct o1 = new OrderProduct();
//            o1.setP(p);
//            o1.setOrderPrice(p.getPrice());
//            o1.setOrderTime(System.currentTimeMillis());
//        
//        List<OrderProduct> orders= new ArrayList<OrderProduct>();
//            orders.add(o);
//            orders.add(o1);
//
//        Order order= new Order();
//            order.setOrderId(1);
//            order.setCheckOut(false);
//            order.setOrderName("Order01");
//            order.setList(orders);
//            
//        orderList.add(order);
    }
    
    
    @Autowired
    OrderDao orderDao;
      
 
    @RequestMapping(value = "/api/orders",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Order>> getOrderList() {
        return new ResponseEntity<Collection<Order>>(orderDao.getAllOrder(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/orderList",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Order>> getOrder() {
        return new ResponseEntity<Collection<Order>>(orderDao.getAllList(), HttpStatus.OK);
    }
    @RequestMapping(value = "/api/orders/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<Order> getOrder(@PathVariable("id") int id) {

        Order ord =orderDao.getOneOrder(id);
        return new ResponseEntity<Order>(ord,HttpStatus.NO_CONTENT);
    }
    
    
    
    @RequestMapping(value = "/api/orders",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody  Order o) {
        
            orderDao.saveOrder(o);

        return new ResponseEntity<Order>(o, HttpStatus.CREATED);
    }
    
    
    
    
    @RequestMapping(value = "/api/confirmOrder",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> confirmOrder(@RequestBody  Order o) {
            
       Order update=   orderDao.updateOrder(o);
       
       return new ResponseEntity<Order>(update, HttpStatus.CREATED);
    }
    
    
  
    @RequestMapping(value = "/api/orders/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") int id) {
        
        Order ord =orderDao.getOneOrder(id);
        orderDao.deleteOrder(ord);
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }
}
