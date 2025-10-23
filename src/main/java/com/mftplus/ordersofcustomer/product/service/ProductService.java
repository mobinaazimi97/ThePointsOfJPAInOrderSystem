package com.mftplus.ordersofcustomer.product.service;

import com.mftplus.ordersofcustomer.product.Product;
import com.mftplus.ordersofcustomer.serviceAbstract.Service;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
public class ProductService implements Service<Product, Long> {

    @PersistenceContext(unitName = "mft")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Transactional
    @Override
    public void edit(Product product) {
        entityManager.merge(product);

    }

    @Transactional
    @Override
    public void remove(Long id) {
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
    }

    @Transactional
    @Override
    public Product findById(Long id) {
        return entityManager.find(Product.class, id);

    }

    @Transactional
    @Override
    public List<Product> findAll() {
        Query query = entityManager.createQuery("select p from productEntity p", Product.class);
        return query.getResultList();
    }

    @Transactional
    public List<Product> findByName(String name) {
        Query query = entityManager.createQuery("select p from productEntity p where p.name = : name", Product.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

//    @Transactional
//    public List<Product> findByPrice(Float price) {
//        Query query = entityManager.createQuery("select p from productEntity p where p.price = : price", Product.class);
//        query.setParameter("price", price);
//        return query.getResultList();
//    }

    @Transactional
    public List<Product> findByCode(Long code) {
        Query query = entityManager.createQuery("select p from productEntity p where p.code = : code", Product.class);
        query.setParameter("code", code);
        return query.getResultList();
    }

    @Transactional
    public List<Product> findByGroup(String name) {
        //todo
        Query query = entityManager.createQuery("select p from productEntity p where p.productGroup.name=:name", Product.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}