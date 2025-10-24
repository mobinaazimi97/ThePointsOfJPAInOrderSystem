package com.mftplus.ordersofcustomer.service;


import com.mftplus.ordersofcustomer.entity.GroupProperty;
import com.mftplus.ordersofcustomer.serviceAbstract.Service;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped

public class GroupPropertyService implements Service<GroupProperty, Long> {
    @PersistenceContext(unitName="mft")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(GroupProperty groupProperty) {
        entityManager.persist(groupProperty);
    }

    @Transactional
    @Override
    public void edit(GroupProperty groupProperty) {
        entityManager.merge(groupProperty);

    }

    @Transactional
    @Override
    public void remove(Long id) {
        GroupProperty groupProperty = entityManager.find(GroupProperty.class, id);
        entityManager.remove(groupProperty);

    }

    @Transactional
    @Override
    public GroupProperty findById(Long id) {
        return entityManager.find(GroupProperty.class, id);

    }

    @Transactional
    @Override
    public List<GroupProperty> findAll() {
        Query query = entityManager.createQuery("select g from groupProEntity g", GroupProperty.class);
        return query.getResultList();
    }

    @Transactional
    public List<GroupProperty> findByName(String name) {
        Query query = entityManager.createQuery("select g from groupProEntity g where g.name=:name", GroupProperty.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Transactional
    public List<GroupProperty> findProductGroupNameByGroupProperty(String name) {
        Query query = entityManager.createQuery("select bb from groupProEntity  bb where productGroupEntity.name = :name", GroupProperty.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}


