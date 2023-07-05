package com.toggle.poc.service;

import com.toggle.poc.dto.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class InventoryService {

    public List<Product> getAllProducts() {

        return Stream.of(new Product(1, "mobile", 50000,"Mobile"),
                new Product(2, "headphone", 2000,"Headphone"),
                new Product(3, "watch", 14999,"watch")
                , new Product(4, "glass", 999,"glass")
        ).collect(Collectors.toList());
    }
}
