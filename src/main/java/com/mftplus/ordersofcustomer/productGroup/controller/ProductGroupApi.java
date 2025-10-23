package com.mftplus.ordersofcustomer.productGroup.controller;


import com.mftplus.ordersofcustomer.productGroup.entity.ProductGroup;
import com.mftplus.ordersofcustomer.productGroup.service.ProductGroupService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Path("/ProductsGroups")
@Slf4j

public class ProductGroupApi {
    @Inject
    private ProductGroupService productGroupService;

    @GET
    @Produces
    public Object getProductGroups() {
        log.info("getProductGroups");
        return productGroupService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProductGroupById(@PathParam("id") Long id) {
        log.info("getProductGroupById");
        return productGroupService.findById(id);
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProductGroupByName(@PathParam("name") String name) {
        log.info("getProductGroupByName");
        return productGroupService.findByName(name);
    }

    @GET
    @Path("/parent/{parent}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProductGroupByParent(@PathParam("parent") String name) {
        log.info("getProductGroupByParent");
        return productGroupService.findByParent(name);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object addProductGroup(@Valid ProductGroup productGroup) {
        log.info("addProductGroup");
        productGroupService.save(productGroup);
        return productGroup;

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object editProductGroup(@Valid ProductGroup productGroup) {
        log.info("edit ProductGroup");
        productGroupService.edit(productGroup);
        return productGroup;
    }

    @DELETE
    @Path("/{id}")
    public Object removeProductGroupById(@PathParam("id") Long id) {
        log.info("remove ProductGroupById");
        ProductGroup productGroup = productGroupService.findById(id);
        productGroupService.remove(id);
        return productGroup.getId();
    }

}
