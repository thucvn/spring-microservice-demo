package com.uu.productservice.controller;

import com.uu.productservice.repository.model.Product;
import com.uu.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public List<Product> getAll(@RequestHeader(value = "AuthorInfo", required = false) String header) {
        System.out.println(header);
        return productService.getAll();
    }
}
