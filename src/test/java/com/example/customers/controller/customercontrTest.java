package com.example.customers.controller;

import com.example.customers.entity.customer;
import com.example.customers.service.customerserv;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(customercontr.class)
public class customercontrTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private customerserv service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCustomers() throws Exception {
        customer c1 = new customer(1L, "John", "john@example.com");
        customer c2 = new customer(2L, "Ajay", "ajay@example.com");

        when(service.getAll()).thenReturn(Arrays.asList(c1, c2));

        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetCustomerById() throws Exception {
        customer c = new customer(1L, "John", "john@example.com");

        when(service.getById(1L)).thenReturn(c);

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testCreateCustomer() throws Exception {
        customer c = new customer(null, "New", "new@example.com");
        customer saved = new customer(1L, "New", "new@example.com");

        when(service.save(any(customer.class))).thenReturn(saved);

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(c)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        customer updated = new customer(1L, "Updated", "updated@example.com");

         when(service.update(eq(1L), any(customer.class))).thenReturn(updated);

        mockMvc.perform(put("/api/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }
}
