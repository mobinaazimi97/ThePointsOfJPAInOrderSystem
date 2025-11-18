package com.mftplus.ordersofcustomer.testApis;


import com.mftplus.ordersofcustomer.dto.ProductDTO;
import com.mftplus.ordersofcustomer.dto.ReportSummary;
import com.mftplus.ordersofcustomer.entity.*;
import com.mftplus.ordersofcustomer.entity.enums.OrderStatus;
import com.mftplus.ordersofcustomer.service.CustomerService;
import com.mftplus.ordersofcustomer.service.OrderService;
import com.mftplus.ordersofcustomer.service.ProductService;
import com.mftplus.ordersofcustomer.service.ReportService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Path("/test5")
@Slf4j
@Produces(MediaType.APPLICATION_JSON)
public class TestProduct {
    @Inject
    private CustomerService customerService;
    @Inject
    private ProductService productService;
    @Inject
    private OrderService orderService;
    @Inject
    private ReportService reportService;

    @GET
    @Path("/product")
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String test() {

        Product product = Product.builder()
                .name("laptop")
                .price(20F)
                .code("111")
                .category("digital")
                .build();
        productService.save(product);
        log.info(product.toString());
//        return product.toString();

        Product product2 = Product.builder()
                .name("mobile")
                .price(20F)
                .code("1111")
                .category("")
                .build();
        productService.save(product2);
        log.info(product2.toString());
//        return product.toString();


        Customer customer = new Customer();
        customer.setFirstName("mm");
        customer.setLastName("ee");
        customer.setEmail("www.a@gmail.com");
        customerService.save(customer);
//        log.info(customer.toString());

        OrderItem orderItem = OrderItem.builder().price(300).quantity(3).product(product).build();
        Order order = Order.builder().customer(customer).orderStatus(OrderStatus.PENDING).discount(200).build();
        order.addItem(orderItem);
        orderService.save(order);
        log.info("order Saved");
//        return String.valueOf(order.getPureAmount());
        return order.toString();


    }

    @GET
    @Path("/customer")
    public String testCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("mm");
        customer.setLastName("aa");
        customer.setEmail("www.ma@.com");
        customer.setPhone("0912");
        customerService.save(customer);
        return customer.toString();

    }

    @GET
    @Path("/category/{category}")
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Object testCategory(@PathParam("category") String category) {

        Product product = Product.builder()
                .name("laptop")
                .price(300F)
                .code("111")
                .category("digital")
                .build();

        productService.save(product);
        log.info(product.toString());
        return productService.findByCategory(category);
    }


    @GET
    @Path("/report")
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Double testRep() {


        Product product = Product.builder()
                .name("laptop")
                .price(300F)
                .code("111")
                .build();

        productService.save(product);

        Customer customer = new Customer();
        customer.setFirstName("mm");
        customer.setLastName("ee");
        customer.setEmail("www.a@gmail.com");
        customerService.save(customer);

        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();

        LocalDateTime orderDate = LocalDateTime.of(year, month, 1, 0, 0, 0);

        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .price(product.getPrice())
                .quantity(3)
                .build();

        Order order = Order.builder()
                .customer(customer)
                .orderStatus(OrderStatus.PENDING)
                .discount(0.0)
                .createdAt(orderDate)
                .build();

        order.addItem(orderItem);
        orderService.save(order);

        Report report = new Report();
        report.setYear(year);
        report.setMonth(month);
        report.setGeneratedAt(LocalDateTime.now());
        reportService.save(report);

        ReportSummary summary = reportService.getReportSummary(report.getId());

        return summary.getTotalSales();
    }

}