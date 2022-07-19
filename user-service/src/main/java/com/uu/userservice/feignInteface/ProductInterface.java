package com.uu.userservice.feignInteface;

import com.uu.microservice.core.config.Constants;
import com.uu.userservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@FeignClient(name = Constants.Endpoint.PRODUCT_MICROSERVICE)
public interface ProductInterface {
    @RequestMapping(value = "product")
    List<ProductDTO> getAll();
}

