package com.mftplus.ordersofcustomer.service;

import com.mftplus.ordersofcustomer.entity.Product;
import com.mftplus.ordersofcustomer.entity.ProductImage;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProductImageService {

    @PersistenceContext(unitName = "mft")
    private EntityManager em;

    public void addImageToProduct(Long productId, ProductImage img) {
        Product product = em.find(Product.class, productId);

        img.setProduct(product);
        em.persist(img);
    }

    public ProductImage find(Long id) {
        return em.find(ProductImage.class, id);
    }
}
