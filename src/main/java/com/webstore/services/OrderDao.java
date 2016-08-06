/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webstore.services;

import com.webstore.model.Order;
import com.webstore.model.OrderProduct;
import com.webstore.model.Product;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WeiliangOuyang
 */
@Repository
@Transactional
public class OrderDao {
        @Autowired
    private SessionFactory _sessionFactory;
    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    public List<Order> getAllOrder() {
        return getSession().createQuery("select distinct order1 from Order order1").list();
    }
    //  List one Order
    public Order getOneOrder( int id) {
        return (Order) getSession().createQuery("select distinct order1 from Order order1 where order1.id=:id").setParameter("id", id).list().get(0);
    }
     
    public Order saveOrder(Order o) {
            List<OrderProduct> orders =o.getList();
            o.setList(orders);
            getSession().save(o);
            for(int i=0;i<orders.size();i++){
                OrderProduct op =orders.get(i);
                op.setOrderTime(System.currentTimeMillis());
                op.setOrder(o);
                getSession().save(op);
            }
            
            return o;
    }
    
    public Order updateOrder(Order o) {
            List<OrderProduct> orders =o.getList();
            o.setList(orders);
            getSession().update(o);
            for(int i=0;i<orders.size();i++){
                OrderProduct op =orders.get(i);
                op.setOrderTime(System.currentTimeMillis());
                op.setOrder(o);
                getSession().saveOrUpdate(op);
            }
            return o;
    }
    
       public void deleteOrder(Order o) {
            List<OrderProduct> orders =o.getList();
            
            for(int i=0;i<orders.size();i++){
                OrderProduct op =orders.get(i);
                op.setOrderTime(System.currentTimeMillis());
                op.setOrder(o);
                getSession().delete(op);
            }
            o.setList(orders);
            getSession().delete(o);
    }
}
     
     
