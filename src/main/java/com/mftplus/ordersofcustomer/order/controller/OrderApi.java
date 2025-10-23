package com.mftplus.ordersofcustomer.order.controller;

import com.mftplus.ordersofcustomer.order.entity.Order;
import com.mftplus.ordersofcustomer.order.entity.enums.OrderStatus;
import com.mftplus.ordersofcustomer.order.service.OrderService;
import com.mftplus.ordersofcustomer.utils.Loggable;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Path("/orders")
@Slf4j
public class OrderApi {
    @Inject
    private OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object getOrders() {
        log.info("get-Orders");
        return orderService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Loggable
    public Object getOrderById(@PathParam("id") Long id) {
        return orderService.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/userId/{userId}")
    @Loggable
    public Object getOrderByUserId(@PathParam("userId") Long id) {
        return orderService.findByUserId(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/username/{username}")
    @Loggable
    public Object getOrderByUsername(@PathParam("username") String username) {
        return orderService.findByUsername(username);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customerId/{id}")
    @Loggable
    public Object getOrderByCustomerId(@PathParam("id") Long id) {
        return orderService.findByCustomerId(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customerMail/{mail}")
    @Loggable
    public Object getOrderByCustomerId(@PathParam("mail") String email) {
        return orderService.findByCustomerMail(email);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/status/{status}")
    @Loggable
    public Object getOrderByUsername(@PathParam("status") OrderStatus orderStatus) {
        return orderService.findByOrderStatus(orderStatus);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/bill/{bill}")
    @Loggable
    public Object getOrderByBillingAddress(@PathParam("bill") String billingAddress) {
        return orderService.findByBillingAddress(billingAddress);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/product/{product}")
    @Loggable
    public Object getProductByOrderInItem(@PathParam("product") String name) {
        return orderService.findProductByOrderInItem(name);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object addOrder(@Valid Order order) {
        orderService.save(order);
        return order;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Loggable
    public Object updateOrder(@Valid Order order) {
        orderService.edit(order);
        return order;
    }

    @DELETE
    @Path("/{id}")
    @Loggable
    public Object deleteOrder(@PathParam("id") Long id) {
        orderService.remove(id);
        return id;
    }
}
