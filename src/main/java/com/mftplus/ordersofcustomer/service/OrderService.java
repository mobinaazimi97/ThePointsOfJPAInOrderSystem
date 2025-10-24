package com.mftplus.ordersofcustomer.service;


import com.mftplus.ordersofcustomer.entity.Order;
import com.mftplus.ordersofcustomer.entity.enums.OrderStatus;
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
public class OrderService implements Service<Order, Long> {

    @PersistenceContext(unitName="mft")
    private EntityManager entityManager;

    @Transactional
    @Override
    @Loggable
    public void save(Order order) {
        entityManager.persist(order);
        log.info("order-saved");
    }

    @Transactional
    @Override
    @Loggable
    public void edit(Order order) {
        if (order.getId() != null) {
            entityManager.merge(order);
            log.info("order-updated");
        } else {
            log.error("order-update-error");
        }
    }

    @Transactional
    @Override
    @Loggable
    public void remove(Long id) {
        Order order = entityManager.find(Order.class, id);
        entityManager.remove(order);
        log.info("order-removed");
    }

    @Transactional
    @Override
    @Loggable
    public Order findById(Long id) {
        return entityManager.find(Order.class, id);
    }

    @Transactional
    @Override
    @Loggable
    public List<Order> findAll() {
        Query query = entityManager.createQuery("select o from orderEntity o", Order.class);
        return query.getResultList();
    }

    @Transactional
    @Loggable
    public Order findByUserId(Long id) {
        Query query = entityManager.createQuery("select o from orderEntity o where o.user.id = :id", Order.class);
        query.setParameter("id", id);
        return (Order) query.getSingleResult();
    }

    @Transactional
    @Loggable
    public Order findByUsername(String username) {
        Query query = entityManager.createQuery("select o from orderEntity o where o.user.username = :username", Order.class);
        query.setParameter("username", username);
        return (Order) query.getSingleResult();
    }

    @Transactional
    @Loggable
    public Order findByCustomerMail(String email) {
        Query query = entityManager.createQuery("select o from orderEntity o where o.customer.email like :email", Order.class);
        query.setParameter("email", email);
        return (Order) query.getSingleResult();
    }

    @Transactional
    @Loggable
    public Order findByCustomerId(Long id) {
        Query query = entityManager.createQuery("select o from orderEntity o where o.customer.id = :id", Order.class);
        query.setParameter("id", id);
        return (Order) query.getSingleResult();
    }

    @Transactional
    @Loggable
    public Order findByOrderStatus(OrderStatus orderStatus) {
        Query query = entityManager.createQuery("select o from orderEntity o where o.orderStatus = :orderStatus", Order.class);
        query.setParameter("orderStatus", orderStatus);
        return (Order) query.getSingleResult();
    }

    @Transactional
    @Loggable
    public List<Order> findByBillingAddress(String billingAddress) {
        Query query = entityManager.createQuery("select o from orderEntity o where o.billingAddress = :billingAddress", Order.class);
        query.setParameter("billingAddress", billingAddress);
        return query.getResultList();
    }

    @Transactional
    @Loggable
    public List<Order> findProductByOrderInItem(String name) {
        Query query = entityManager.createQuery("select o.orderItems from orderEntity o join productEntity p where p.name = :name", Order.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
