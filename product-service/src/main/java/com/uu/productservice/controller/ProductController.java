package com.uu.productservice.controller;

import com.uu.microservice.core.config.Error;
import com.uu.microservice.core.exception.ResponseCodeException;
import com.uu.productservice.repository.model.Product;
import com.uu.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public Page<Product> getAll(@RequestParam String keyword, @RequestParam int page, @RequestParam int pageSize) {
        return productService.getAll(keyword, page, pageSize);
    }

    @GetMapping("error")
    public Product test() {
        throw new ResponseCodeException(HttpStatus.INTERNAL_SERVER_ERROR, Error.TEST_EXCEPTION);
    }

    @GetMapping("errorSystem")
    public Product testSystemError() throws Exception {
        throw new Exception("Error system testing!");
    }
}
