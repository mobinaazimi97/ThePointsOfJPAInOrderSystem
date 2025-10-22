package com.mftplus.ordersofcustomer.ws.rs;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApi extends Application {
    public RestApi() {
        System.out.println("Api Loaded !");
    }
}
