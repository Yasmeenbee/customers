package com.example.customers.service;

import com.example.customers.entity.customer;
import com.example.customers.repository.customerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class customerservTest {

    @InjectMocks
    private customerserv service;

    @Mock
    private customerRepo repo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCustomer() {
        customer cust = new customer();
        cust.setName("John");
        cust.setEmail("john@example.com");

        when(repo.save(cust)).thenReturn(cust);

        customer saved = service.save(cust);

        assertEquals("John", saved.getName());
        assertEquals("john@example.com", saved.getEmail());
    }

    @Test
    void testGetAllCustomers() {
        List<customer> list = Arrays.asList(
                new customer(1L, "John", "john@example.com"),
                new customer(2L, "Alice", "alice@example.com")
        );

        when(repo.findAll()).thenReturn(list);

        List<customer> result = service.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void testGetCustomerById() {
        customer cust = new customer(1L, "John", "john@example.com");
        when(repo.findById(1L)).thenReturn(Optional.of(cust));

        customer result = service.getById(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void testUpdateCustomer() {
        customer existing = new customer(1L, "Old", "old@example.com");
        customer updated = new customer();
        updated.setName("New");
        updated.setEmail("new@example.com");

        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.save(any(customer.class))).thenReturn(existing);

        customer result = service.update(1L, updated);

        assertEquals("New", result.getName());
        assertEquals("new@example.com", result.getEmail());
    }

    @Test
    void testDeleteCustomer() {
        Long id = 1L;
        doNothing().when(repo).deleteById(id);

        service.delete(id);

        verify(repo, times(1)).deleteById(id);
    }
}
