package com.uu.productservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uu.microservice.core.config.Constants;
import com.uu.microservice.core.config.Error;
import com.uu.microservice.core.exception.ResponseCodeException;
import com.uu.microservice.core.jwt.JwtTokenData;
import com.uu.productservice.repository.model.Product;
import com.uu.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public List<Product> getAll(@RequestHeader(value = Constants.HEADER_AUTHOR, required = false) String header
                                ) {
        try {
            JwtTokenData data = JwtTokenData.fromJson(header);
            System.out.println(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return productService.getAll();
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
