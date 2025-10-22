package com.mftplus.ordersofcustomer.testApis;


import com.mftplus.ordersofcustomer.customer.entity.Customer;
import com.mftplus.ordersofcustomer.customer.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/test5")
public class TestProduct {
    @Inject
    private CustomerService customerService;

    @GET
    public String test() {
        Customer customer = new Customer();
        customer.setFirstName("mm");
        customer.setLastName("aa");
        customer.setEmail("www.ma@.com");
        customer.setPhone("0912");

        customerService.save(customer);
        return customer.toString();


    }
}
