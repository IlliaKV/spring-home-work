package com.vebdev.springhomework.controller;

import com.vebdev.springhomework.domain.Manufacturer;
import com.vebdev.springhomework.domain.Product;
import com.vebdev.springhomework.servise.jpa.ManufactorerService;
import com.vebdev.springhomework.servise.jpa.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
public class ProductsController extends BaseSecurityController{
    @Autowired
    private ProductService productService;
    @Autowired
    private ManufactorerService manufactorerService;

    @GetMapping("/products")
    public ModelAndView products(@RequestParam(required = false, defaultValue = "4") String value) {
        ModelAndView modelAndView = modelAndViewSecurityBase("products");

        modelAndView.addObject("products", productService.getAll());

        return modelAndView;
    }

    @GetMapping("/productsofmanufactorer")
    public ModelAndView productsofmanufactorer(@RequestParam(name = "manufactorerId") long manufactorerId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("productsofmanufactorer");

        modelAndView.addObject("manufacturer", manufactorerService.getById(manufactorerId));
        modelAndView.addObject("products", productService.getAllByManufacturerId(manufactorerId));

        return modelAndView;
    }

    @GetMapping("/product/delete")
    public String deleteProduct(@RequestParam long productId,
            @RequestParam long manufacturerId) {
        if (productService.exists(productId)) {
            Product product = productService.getById(productId);
            productService.delete(product);
        }
        return "redirect:/productsofmanufactorer?manufactorerId=" + manufacturerId;
    }


    @GetMapping("/product/add")
    public ModelAndView createProduct() {
        ModelAndView modelAndView = modelAndViewSecurityBase("/product/create");
        modelAndView.addObject("manufactorers", manufactorerService.getAll());
        return modelAndView;
    }

    @PostMapping("/product/add")
    public String createProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/productsofmanufactorer?manufactorerId=" + product.getManufacturer().getId();
    }

    @GetMapping("/product/edit")
    public ModelAndView editProduct(@RequestParam long productId,
                                    @RequestParam long manufacturerId) {
        ModelAndView modelAndView = modelAndViewSecurityBase("product/edit");

        modelAndView.addObject("manufacturers", manufactorerService.getAll());
        modelAndView.addObject("manufacturer", manufactorerService.getById(manufacturerId));
        modelAndView.addObject("product", productService.getById(productId));

        return modelAndView;
    }

    @PostMapping("/product/edit")
    public String editManufactorer(@RequestParam(value = "productId", required = true) long productId,
                                   @RequestParam(value = "nameProduct", required = false) String nameProduct,
                                   @RequestParam(value = "price", required = false) BigDecimal price,
                                   @RequestParam(value = "manufacturerOfManufacturer", required = false) String manufacturerOfManufacturer,
                                   @RequestParam(value = "manufacturer", required = false) Manufacturer manufacturer) {

        Product product = productService.getById(productId);
        product.setNameProduct(nameProduct);
        product.setPrice(price);
        product.setManufacturerOfManufacturer(manufacturerOfManufacturer);
        product.setManufacturer(manufacturer);

        productService.update(product);
        return "redirect:/productsofmanufactorer?manufactorerId=" + product.getManufacturer().getId();
    }
}
