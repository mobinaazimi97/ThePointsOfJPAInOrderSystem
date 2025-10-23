package com.mftplus.ordersofcustomer.customer.service;


import com.mftplus.ordersofcustomer.customer.entity.Customer;
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
public class CustomerService implements Service<Customer, Long> {
    @PersistenceContext(unitName="mft")
    private EntityManager entityManager;

    @Transactional
    @Loggable
    @Override
    public void save(Customer customer) {
        entityManager.persist(customer);
        log.info("Customer {} saved", customer);
    }

    @Transactional
    @Loggable
    @Override
    public void edit(Customer customer) {
        if (customer.getId() != null) {
            entityManager.merge(customer);
            log.info("Customer {} updated", customer);
        } else {
            log.error("Customer {} not updated", customer);
        }
    }

    @Transactional
    @Loggable
    @Override
    public void remove(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);
        log.info("customer-removed");
    }


    @Transactional
    @Loggable
    @Override
    public List<Customer> findAll() {
        Query query = entityManager.createQuery("select c from customerEntity c", Customer.class);
        return query.getResultList();
    }

    @Transactional
    @Loggable
    @Override
    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

}
