package com.example.customers.controller;

import com.example.customers.entity.customer;
import com.example.customers.service.customerserv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class customercontr {

    @Autowired
    private customerserv service;

    @GetMapping
    public List<customer> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public customer getById(@PathVariable Long id){
    return service.getById(id);
    }

    @PostMapping
    public customer creat(@RequestBody customer customer){
        return service.save(customer);
}

    @PutMapping("/{id}")
    public customer update(@PathVariable Long id, @RequestBody customer customer){
        return  service.update(id,customer);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted";
    }


}
