package com.portfolio.microservices.product.controller;


import com.portfolio.microservices.product.dto.ProductRequest;
import com.portfolio.microservices.product.dto.ProductResponse;
import com.portfolio.microservices.product.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "Products", description = "Operations related to the product service")
    
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return _productService.createProduct(productRequest);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Tag(name = "Products", description = "Operations related to the product service")
    public Iterable<ProductResponse> getProducts(){
        return _productService.getAllProducts();
    }
}
