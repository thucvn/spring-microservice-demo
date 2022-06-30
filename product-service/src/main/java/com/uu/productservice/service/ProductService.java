package com.uu.productservice.service;

import com.uu.productservice.repository.ProductRepository;
import com.uu.productservice.repository.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
