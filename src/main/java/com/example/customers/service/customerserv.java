package com.example.customers.service;

import com.example.customers.entity.customer;
import com.example.customers.repository.customerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class customerserv {

    @Autowired
    private customerRepo repository;

    // Get All Data in the db
    public List<customer> getAll(){
        return repository.findAll();
    }

    // Find the Id data int the db
    public customer getById(Long id){
        return repository.findById(id).orElse(null);
    }

    // Creat new data in the db
    public customer save(customer customer){
        return repository.save(customer);
    }

    //Udate the recored in the db
    public customer update(Long id, customer updated){
        customer customer1=repository.findById(id).orElse(null);
        if (customer1 !=null){
            customer1.setName(updated.getName());
            customer1.setEmail(updated.getEmail());
            return repository.save(customer1);
        }
        return null;
    }

    // delete  data for db
    public void  delete(Long id) {
        repository.deleteById(id);

    }
}
