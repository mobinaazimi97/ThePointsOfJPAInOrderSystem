package com.mftplus.ordersofcustomer.testApis;


import com.mftplus.ordersofcustomer.customer.entity.Customer;
import com.mftplus.ordersofcustomer.customer.service.CustomerService;
import com.mftplus.ordersofcustomer.order.entity.Order;
import com.mftplus.ordersofcustomer.order.entity.enums.OrderStatus;
import com.mftplus.ordersofcustomer.order.service.OrderService;
import com.mftplus.ordersofcustomer.orderItem.OrderItem;
import com.mftplus.ordersofcustomer.product.GroupProperty;
import com.mftplus.ordersofcustomer.product.Product;
import com.mftplus.ordersofcustomer.product.ProductGroup;
import com.mftplus.ordersofcustomer.product.ProductPropertyValue;
import com.mftplus.ordersofcustomer.product.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

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
    public String test() {
        ProductGroup child = new ProductGroup();
        ProductGroup parent = new ProductGroup();
        child.setName("digital");
        parent.setName("electronic");
        ProductPropertyValue productPropertyValue = ProductPropertyValue.builder().name("64G").build();
        GroupProperty groupProperty = GroupProperty.builder().name("ram").productPropertyValue(productPropertyValue).build();
        ProductGroup productGroup = ProductGroup.builder().groupProperty(groupProperty).name("laptop").parent(parent).build();
        Product product = Product.builder()
                .name("laptop")
                .price(20F)
                .productGroup(productGroup)
                .code(1L)
                .build();
        productService.save(product);
        log.info(product.toString());

        OrderItem orderItem = OrderItem.builder().price(300).quantity(3).product(product).build();
        Order order = Order.builder().orderStatus(OrderStatus.PENDING).discount(200).build();
        order.addItem(orderItem);
        orderService.save(order);
        log.info("order Saved");
        return String.valueOf(order.getPureAmount());
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
