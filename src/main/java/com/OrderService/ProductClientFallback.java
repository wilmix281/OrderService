package com.OrderService;

import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient {
    @Override
    public String getProduct(String id) {
        return "Fallback: Product service is unavailable!";
    }
}