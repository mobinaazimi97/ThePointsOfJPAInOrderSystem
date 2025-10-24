package com.mftplus.ordersofcustomer.controller;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/rest/v2")
public class RestApi extends Application {
    public RestApi() {
        System.out.println("Api Loaded !");
    }
}
