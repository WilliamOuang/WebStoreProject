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
    
    @Autowired
    ProductDao productDao;
    
    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    public List<Order> getAllOrder() {
        return getSession().createQuery("select distinct order1 from Order order1").list();
    }
    public List<Order> getAllList() {
        return getSession().createQuery("select distinct order1,ordproduct,product from Order order1 join order1.list ordproduct  join ordproduct.p product order by order1.id ").list();
    }
    //  List one Order
    public Order getOneOrder( int id) {
        return (Order) getSession().createQuery("select distinct order1 from Order order1 where order1.id=:id").setParameter("id", id).list().get(0);
    }
     
    public Order saveOrder(Order o) {
            List<OrderProduct> orders =o.getList();
            o.setList(orders);
            o.setOrderTime(System.currentTimeMillis());
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
//        Long orderTime;
// Order order=  (Order) getSession().createQuery("select distinct order1 from Order order1 join order1.list ordproduct join ordproduct.p product where order1.id=:id").setParameter("id", o.getId()).list().get(0);;
//        List<OrderProduct> orders =order.getList();
//      //  for(int i=0;i<orders.size();i++){
//              OrderProduct optime =orders.get(0);
//              orderTime=optime.getOrderTime();
//      //  }

      List<OrderProduct> updateorders =o.getList();
        
      for(int i=0;i<updateorders.size();i++){
                OrderProduct op =updateorders.get(i);
               
                op.setOrder(o);
                op.setOrderTime(o.getOrderTime());
                // Step 5 for getting Updated Porduct price
                if(System.currentTimeMillis()-op.getOrderTime()>1000*60){
                      op.setOrderTime(System.currentTimeMillis());
                    op.setOrderPrice(productDao.getById(op.getP().getProductId()).getPrice());
                }
                op.setOrderTime(System.currentTimeMillis());

                getSession().save(op);
      }
        o.setList(updateorders);

        getSession().update(o);
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
     
     

