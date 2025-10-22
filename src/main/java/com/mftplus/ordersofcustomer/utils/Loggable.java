package com.mftplus.ordersofcustomer.utils;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD , ElementType.TYPE} )
public @interface Loggable {
}
