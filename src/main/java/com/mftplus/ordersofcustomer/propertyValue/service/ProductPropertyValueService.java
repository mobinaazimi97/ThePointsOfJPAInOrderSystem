package com.mftplus.ordersofcustomer.propertyValue.service;

import com.mftplus.ordersofcustomer.propertyValue.entity.ProductPropertyValue;
import com.mftplus.ordersofcustomer.serviceAbstract.Service;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
public class ProductPropertyValueService implements Service<ProductPropertyValue, Long> {
    @PersistenceContext(unitName="mft")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(ProductPropertyValue productPropertyValue) {
        entityManager.persist(productPropertyValue);

    }

    @Transactional
    @Override
    public void edit(ProductPropertyValue productPropertyValue) {
        entityManager.merge(productPropertyValue);

    }

    @Transactional
    @Override
    public void remove(Long id) {
        ProductPropertyValue productPropertyValue = entityManager.find(ProductPropertyValue.class, id);
        entityManager.remove(productPropertyValue);

    }

    @Transactional
    @Override
    public ProductPropertyValue findById(Long id) {
        return entityManager.find(ProductPropertyValue.class, id);
    }

    @Transactional
    public ProductPropertyValue findByName(String name) {
        Query query = entityManager.createQuery("select p from productProEntity p where p.name = : name", ProductPropertyValue.class);
        query.setParameter("name", name);
        return (ProductPropertyValue) query.getSingleResult();
    }

    @Transactional
    @Override
    public List<ProductPropertyValue> findAll() {
        Query query = entityManager.createQuery("select pp from productProEntity  pp", ProductPropertyValue.class);
        return query.getResultList();
    }
}
