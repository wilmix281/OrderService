package com.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final ProductClient productClient;

    public OrderController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "productService", fallbackMethod = "orderFallback")
    public String getOrder(@PathVariable String id) {
        return "Order details with product: " + productClient.getProduct(id);
    }

    public String orderFallback(String id, Throwable t) {
        return "Order details: Product service is down, please try later!";
    }
}