package com.mftplus.ordersofcustomer.service;

import com.mftplus.ordersofcustomer.entity.Product;
import com.mftplus.ordersofcustomer.serviceAbstract.Service;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
        Query query = entityManager.createQuery("select p from productEntity p where p.name like:name", Product.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

//    @Transactional
//    public List<ProductDTO> findByCategory(ProductDTO productDTO) {
//        Product product = new Product();
//        product.setCategory(productDTO.getCategory());
//        entityManager.persist(product);
//        entityManager.flush();
//        TypedQuery<Product> query = entityManager.createQuery("select p from productEntity p where p.category like :category", Product.class);
//        List category = query.setParameter("category", "%" + product.getCategory() + "%")
//                .getResultList();
//        productDTO.setCategory(String.valueOf(category.get(0)));
//        return Collections.singletonList(productDTO);
//    }

    @Transactional
    public List<Product> findByCategory(String category) {
        TypedQuery<Product> query = entityManager.createQuery(
                "select p from productEntity p where p.category like :category",
                Product.class
        );
        query.setParameter("category", "%" + category + "%");
        return query.getResultList();
    }


    @Transactional
    public List<Product> findByCode(Long code) {
        Query query = entityManager.createQuery("select p from productEntity p where p.code like :code", Product.class);
        query.setParameter("code", code);
        return query.getResultList();
    }
}