package com.mftplus.ordersofcustomer.testApis;


import com.mftplus.ordersofcustomer.customer.entity.Customer;
import com.mftplus.ordersofcustomer.customer.service.CustomerService;
import com.mftplus.ordersofcustomer.order.entity.Order;
import com.mftplus.ordersofcustomer.order.entity.enums.OrderStatus;
import com.mftplus.ordersofcustomer.order.service.OrderService;
import com.mftplus.ordersofcustomer.orderItem.entity.OrderItem;
import com.mftplus.ordersofcustomer.groupProperty.entity.GroupProperty;
import com.mftplus.ordersofcustomer.product.entity.Product;
import com.mftplus.ordersofcustomer.productGroup.entity.ProductGroup;
import com.mftplus.ordersofcustomer.propertyValue.entity.ProductPropertyValue;
import com.mftplus.ordersofcustomer.product.service.ProductService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Path("/test5")
@Slf4j
public class TestProduct {
    @Inject
    private CustomerService customerService;
    @Inject
    private ProductService productService;
    @Inject
    private OrderService orderService;

    @GET
    @Path("/product")
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String test() {
        ProductGroup child = new ProductGroup();
        ProductGroup parent = new ProductGroup();
        child.setName("digital");
        parent.setName("electronic");
        child.setParent(parent);
        ProductPropertyValue productPropertyValue = ProductPropertyValue.builder().name("64G").build();
        GroupProperty groupProperty = GroupProperty.builder().name("ram").productPropertyValue(productPropertyValue).build();
        ProductGroup productGroup = ProductGroup.builder().groupProperty(groupProperty).name("laptop").childList(List.of(child)).build();
        Product product = Product.builder()
                .name("laptop")
                .price(20F)
                .productGroup(productGroup)
                .code(1L)
                .build();
        productService.save(product);
        log.info(product.toString());
//        return product.toString();

        ProductGroup child2 = new ProductGroup();
        ProductGroup parent2 = new ProductGroup();
        child2.setName("phone");
        parent2.setName("electronic");
        ProductPropertyValue productPropertyValue2 = ProductPropertyValue.builder().name("64G").build();
        GroupProperty groupProperty2 = GroupProperty.builder().name("ram").productPropertyValue(productPropertyValue2).build();
        ProductGroup productGroup2 = ProductGroup.builder().groupProperty(groupProperty2).name("laptop").parent(parent2).childList(List.of(child2)).build();
        Product product2 = Product.builder()
                .name("laptop")
                .price(20F)
                .productGroup(productGroup2)
                .code(1L)
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
}
