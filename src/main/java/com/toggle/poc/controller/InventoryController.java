package com.toggle.poc.controller;

import com.toggle.poc.config.FeatureFlags;
import com.toggle.poc.dto.Product;
import com.toggle.poc.service.InventoryService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
public class InventoryController {

    @Autowired
    private FeatureManager manager;

    @Autowired
    private InventoryService service;

    @SneakyThrows
    @GetMapping("/orders")
    public List<Product> showAvailableProducts() {
        log.info("HOST IP:{}",java.net.InetAddress.getLocalHost().toString());

        List<Product> pdt=null;
        if (manager.isActive(FeatureFlags.DISCOUNT_APPLIED)) {
            pdt= applyDiscount(service.getAllProducts());
        }
        else if (manager.isActive(FeatureFlags.DESCRIPTION_UPDATE)){
            pdt= descriptionUpdate(service.getAllProducts());
        }
        else if (manager.isActive(FeatureFlags.DEMO_FLAG)){
            log.info("DEMO_FLAG");
            pdt=null;
        }
        else {
            log.info("Default getAllProducts");
            pdt= service.getAllProducts();
        }
        return pdt;
    }

    private List<Product> applyDiscount(List<Product> availableProducts) throws UnknownHostException {
        log.info("Discount Applied");
        List<Product> orderListAfterDiscount = new ArrayList<>();
        service.getAllProducts().forEach(order -> {
            order.setPrice(order.getPrice() - (order.getPrice() * 5 / 100));
            orderListAfterDiscount.add(order);
        });
        return orderListAfterDiscount;
    }

    @SneakyThrows
    private List<Product> descriptionUpdate(List<Product> availableProducts) {
        var desc=List.of("Mobile Update","Headphone Update","Watch Update","Glass Update");
        log.info("descriptionUpdate");
        List<Product> orderListAfterDiscount = new ArrayList<>();
        AtomicInteger i= new AtomicInteger(0);
        service.getAllProducts().forEach(order -> {
            order.setDescription(desc.get(i.get()));
            i.getAndIncrement();
            orderListAfterDiscount.add(order);
        });
        return orderListAfterDiscount;
    }
}
