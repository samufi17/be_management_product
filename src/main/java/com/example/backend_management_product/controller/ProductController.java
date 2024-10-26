package com.example.backend_management_product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_management_product.model.Product;
import com.example.backend_management_product.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  
  @Autowired
  private ProductService productService;

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    Product product = productService.getProductById(id).orElse(null);
    if (product != null) {
      return new ResponseEntity<>(product, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public Product createProduct(@Valid @RequestBody Product product) {
    return productService.createProduct(product);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product updateProduct) {
    return productService.getProductById(id)
        .map(product -> {
          product.setName(updateProduct.getName());
          product.setPrice(updateProduct.getPrice());
          product.setDescription(updateProduct.getDescription());
          return ResponseEntity.ok(productService.createProduct(product));
        })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/pending")
  public List<Product> getPendingProducts() {
    return productService.getPendingProducts();
  }

  @PutMapping("/{id}/approve")
  public Product approveProduct(@PathVariable Long id) {
    return productService.approveProduct(id);
  }

  @PutMapping("/{id}/reject")
  public Product rejectProduct(@PathVariable Long id) {
    return productService.rejectProduct(id);
  }
}