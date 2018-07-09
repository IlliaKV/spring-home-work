package com.vebdev.springhomework.listener;

import com.vebdev.springhomework.servise.jpa.ManufactorerService;
import com.vebdev.springhomework.servise.jpa.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppReadyListener {

    @Value("${lounchMode}")
    private String lounchMode;

    @Autowired
    private ProductService productService;

    @Autowired
    private ManufactorerService manufactorerService;

    @EventListener(ApplicationReadyEvent.class)
    public void appReady(){

//        Manufacturer manufacturer = new Manufacturer();
//        manufacturer.setManufacturerName("manuf4");
//
//        Product product = new Product();
//        product.setNameProduct("prod44");
//        product.setPrice(new BigDecimal("600.50"));
//        product.setManufacturerOfManufacturer("manuofmanu44");
//        product.setManufacturer(manufacturer);

//
//        Product product2 = new Product();
//        product2.setNameProduct("prod45");
//        product2.setPrice(new BigDecimal("500.50"));
//        product2.setManufacturerOfManufacturer("manuofmanu45");
//        product2.setManufacturer(manufacturer);
//
//        manufactorerService.save(manufacturer);
//        productService.save(product);
//        productService.save(product2);
//        List<Product> products = productService.findProductsByName("prod1");
//        for (Product p:
//             products) {
//            System.out.println(p);
//        }

//        List<Product> products = productService.getAllByManufacturerId(4);
//        for (Product p:
//             products) {
//            System.out.println(p);
//        }

        if(lounchMode != null){
            System.out.println("App ready");
        }
    }
}
