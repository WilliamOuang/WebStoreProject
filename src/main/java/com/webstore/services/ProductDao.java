/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webstore.services;

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
public class ProductDao {
    @Autowired
    private SessionFactory _sessionFactory;
    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }
    
    public Product save(Product pro) {
      getSession().save(pro);
        return pro;
    }
    
    public Product getById(int id) {
        return (Product) getSession().get(Product.class, id);
    }
    
    public List<Product> getAllProduct() {
        return getSession().createQuery("from Product ").list();
    }
    
    public void update(Product pro) {
        getSession().update(pro);
        return;
    }
    public void delete(Product pro) {
        getSession().delete(pro);
        return;
    }
}
