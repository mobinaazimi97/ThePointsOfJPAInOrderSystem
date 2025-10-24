package com.mftplus.ordersofcustomer.service;

import com.mftplus.ordersofcustomer.entity.OrderItem;
import com.mftplus.ordersofcustomer.serviceAbstract.Service;
import com.mftplus.ordersofcustomer.utils.Loggable;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequestScoped
@Slf4j
public class OrderItemService implements Service<OrderItem, Long> {
    @PersistenceContext(unitName="mft")
    private EntityManager entityManager;

    @Transactional
    @Override
    @Loggable
    public void save(OrderItem orderItem) {
        entityManager.persist(orderItem);
        log.info("orderItem-saved");
    }

    @Transactional
    @Override
    @Loggable
    public void edit(OrderItem orderItem) {
        if (orderItem.getId() != null) {
            entityManager.merge(orderItem);
            log.info("orderItem-updated");
        } else {
            log.error("orderItem-update-error");
        }
    }

    @Transactional
    @Override
    @Loggable
    public void remove(Long id) {
        OrderItem orderItem = entityManager.find(OrderItem.class, id);
        entityManager.remove(orderItem);
        log.info("orderItem-removed");
    }

    @Transactional
    @Override
    @Loggable
    public OrderItem findById(Long id) {
        return entityManager.find(OrderItem.class, id);
    }

    @Transactional
    @Override
    @Loggable
    public List<OrderItem> findAll(){
        Query query = entityManager.createQuery("SELECT o FROM OrderItemEntity o", OrderItem.class);
        return query.getResultList();
    }

    @Transactional
    @Loggable
    public OrderItem findByProductId(Long id) {
        Query query = entityManager.createQuery("SELECT o FROM OrderItemEntity o WHERE o.product.id = :id", OrderItem.class);
        query.setParameter("id", id);
        return (OrderItem) query.getSingleResult();
    }
    @Transactional
    @Loggable
    public List<OrderItem> findByProductName(String name) {
        Query query = entityManager.createQuery("SELECT o FROM OrderItemEntity o WHERE o.product.name = :name", OrderItem.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

}
