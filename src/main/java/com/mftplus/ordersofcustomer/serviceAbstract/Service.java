package com.mftplus.ordersofcustomer.serviceAbstract;

import java.util.List;

public interface Service<T,I>{
     void save(T t);
     void edit(T t);
     void remove(I id);
     T findById(I id);
     List<T> findAll();
}
