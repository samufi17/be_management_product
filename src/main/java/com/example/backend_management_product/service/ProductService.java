package com.example.backend_management_product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_management_product.model.Product;
import com.example.backend_management_product.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  public Product createProduct(Product product) {
    product.setStatus(Product.Status.PENDING);
    return productRepository.save(product);
  }

  public Product updateProduct(Long id, Product updateProduct) {
    Optional<Product> existingProductOptional = productRepository.findById(id);

    if (existingProductOptional.isPresent()) {
      Product existingProduct = existingProductOptional.get();
      existingProduct.setName(updateProduct.getName());
      existingProduct.setPrice(updateProduct.getPrice());
      existingProduct.setDescription(updateProduct.getDescription());
      existingProduct.setStatus(updateProduct.getStatus());
      return productRepository.save(existingProduct);
    } else {
      throw new RuntimeException("Product with ID " + id + " not found");
    }
  }

  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }

  public List<Product> getPendingProducts() {
    return productRepository.findByStatus(Product.Status.PENDING);
  }

  public Product approveProduct(Long id) {
    Product product = productRepository.findById(id).orElseThrow();
    product.setStatus(Product.Status.APPROVED);
    return productRepository.save(product);
  }

  public Product rejectProduct(Long id) {
    Product product = productRepository.findById(id).orElseThrow();
    product.setStatus(Product.Status.REJECTED);
    return productRepository.save(product);
  }
}