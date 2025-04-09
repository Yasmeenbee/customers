package com.example.customers.controller;

import com.example.customers.entity.customer;
import com.example.customers.service.customerserv;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer Controller", description = "CRUD operations for customers")
public class customercontr {

    @Autowired
    private customerserv service;

    @GetMapping
    @Operation(summary = "Get all customers")
    public List<customer> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID")
    public customer getById(@PathVariable Long id){
    return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new customer")
    public customer creat(@RequestBody customer customer){
        return service.save(customer);
}

    @PutMapping("/{id}")
    @Operation(summary = "Update existing customer")
    public customer update(@PathVariable Long id, @RequestBody customer customer){
        return  service.update(id,customer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer by ID")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted";
    }


}
