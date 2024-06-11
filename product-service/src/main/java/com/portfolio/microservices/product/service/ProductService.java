package com.portfolio.microservices.product.service;

import com.github.f4b6a3.uuid.UuidCreator;
import com.portfolio.microservices.product.dto.ProductRequest;
import com.portfolio.microservices.product.dto.ProductResponse;
import com.portfolio.microservices.product.model.Product;
import com.portfolio.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
    
    private final ProductRepository _productRepository;
    
    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .id(UuidCreator.getTimeBased())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        
        _productRepository.save(product);
        log.info("Product created: {}", product);
        
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
    
    public Iterable<ProductResponse> getAllProducts(){
        return _productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }
    
}
