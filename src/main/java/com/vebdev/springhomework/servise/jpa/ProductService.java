package com.vebdev.springhomework.servise.jpa;

import com.vebdev.springhomework.domain.Product;
import com.vebdev.springhomework.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product){
        productRepository.save(product);
        return product;
    }

    public Product getById(long id){
        return productRepository.findById(id).get();
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public void delete(Product product){
        productRepository.delete(product);
    }

    public List<Product> findProductsByName(String productName){
        return productRepository.findProductsByName(productName);
    }

    public List<Product> getAllByManufacturerId(long manufacturerId){
        return productRepository.getProductsByManufacturerId(manufacturerId);
    }

    public boolean exists(long productId) {
        return productRepository.existsById(productId);
    }

    public void update(Product product){
        productRepository.save(product);
    }
}
