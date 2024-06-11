package com.portfolio.microservices.product.controller;


import com.portfolio.microservices.product.dto.ProductRequest;
import com.portfolio.microservices.product.dto.ProductResponse;
import com.portfolio.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService _productService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return _productService.createProduct(productRequest);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ProductResponse> getProducts(){
        return _productService.getAllProducts();
    }
}
