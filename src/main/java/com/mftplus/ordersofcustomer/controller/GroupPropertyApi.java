package com.mftplus.ordersofcustomer.controller;


import com.mftplus.ordersofcustomer.entity.GroupProperty;
import com.mftplus.ordersofcustomer.service.GroupPropertyService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Path("/properties")
@Slf4j
public class GroupPropertyApi {
    @Inject
    private GroupPropertyService groupPropertyService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProperties() {
        return groupPropertyService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProductById(@PathParam("id") Long id) {
        return groupPropertyService.findById(id);
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProductByName(@PathParam("name") String name) {
        return groupPropertyService.findByName(name);
    }

    @GET
    @Path("/pGroupName/{pGroupName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProductGroupNameByGroupProperty(@PathParam("pGroupName") String name) {
        return groupPropertyService.findProductGroupNameByGroupProperty(name);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object addProperty(@Valid GroupProperty groupProperty) {
        groupPropertyService.save(groupProperty);
        return groupProperty;

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object updateProperty(@Valid GroupProperty groupProperty) {
        groupPropertyService.edit(groupProperty);
        return groupProperty;

    }

    @DELETE //TODO
    @Path("/{id}")
    public Object deleteGroupProperty(@PathParam("id") Long id) {
        GroupProperty groupProperty = groupPropertyService.findById(id);
        groupPropertyService.remove(id);
        return groupProperty.getId();
    }
}
