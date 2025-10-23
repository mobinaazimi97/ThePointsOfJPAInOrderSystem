package com.mftplus.ordersofcustomer.product.controller;


import com.mftplus.ordersofcustomer.product.Product;
import com.mftplus.ordersofcustomer.product.service.ProductService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Path("/products")
@Slf4j
public class productApi {
    @Inject
    private ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProducts() {
        log.info("getProducts +");
        return productService.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProductById(@PathParam("id") Long id) {
        log.info("getProduct By Id +");
        return productService.findById(id);
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProductByName(@PathParam("name") String name) {
        log.info("getProductByName +");
        return productService.findByName(name);
    }


    @GET
    @Path("/code/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProductByCode(@PathParam("code") Long code) {
        log.info("getProductByCode +");
        return productService.findByCode(code);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object addProduct(@Valid Product product) {
        log.info("add Product +");
        productService.save(product);
        return product;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object updateProduct(@Valid Product product) {
        log.info("update Product +");
        productService.edit(product);
        return product;
    }

    @DELETE
    @Path("{id}")
    public Object deleteProduct(@PathParam("id") Long id) {
        log.info("deleteProduct +");
        Product product = productService.findById(id);
        productService.remove(id);
        return product.getId();
    }

}
