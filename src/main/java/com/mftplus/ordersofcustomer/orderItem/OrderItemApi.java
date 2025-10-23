package com.mftplus.ordersofcustomer.orderItem;


import com.mftplus.ordersofcustomer.utils.Loggable;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Path("/orderItems")
@Slf4j
public class OrderItemApi {
    @Inject
    private OrderItemService orderItemService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object getAllOrderItems() {
        log.info("Getting-all Order Items");
        return orderItemService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @Loggable
    public Object getOrderItemById(@PathParam("id") Long id) {
        return orderItemService.findById(id);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object addOrderItems(OrderItem orderItem) {
        orderItemService.save(orderItem);
        return orderItem;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object updateOrderItems(OrderItem orderItem) {
        orderItemService.edit(orderItem);
        return orderItem;
    }

    @DELETE
    @Path("{id}")
    @Loggable
    public Object deleteOrderItems(@PathParam("id") Long id) {
        orderItemService.remove(id);
        return id;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/proId/{proId}")
    @Loggable
    public Object getOrderItemByProductId(@PathParam("proId") Long id) {
        return orderItemService.findByProductId(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/proName/{proName}")
    @Loggable
    public Object getOrderItemByProductName(@PathParam("proName") String name) {
        return orderItemService.findByProductName(name);
    }

}
