package com.ecommerce.project.service;

import com.ecommerce.project.dto.ProductDto;
import com.ecommerce.project.exceptions.ProductNotExistsException;
import com.ecommerce.project.module.Category;
import com.ecommerce.project.module.Product;
import com.ecommerce.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public void createProduct(ProductDto productDto, Category category) {
        Product product= new Product();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPrice((productDto.getPrice()));
        productRepository.save(product);

    }
     public ProductDto getProductDto(Product product){
         ProductDto productDto= new ProductDto();
         productDto.setDescription(product.getDescription());
         productDto.setImageURL(product.getImageURL());
         productDto.setName(product.getName());
         productDto.setCategoryId(product.getCategory().getId());
         productDto.setPrice((product.getPrice()));
         productDto.setId(product.getId());
         return productDto;
     }
    public List<ProductDto> getAllproducts() {
       List<Product> allProduct=productRepository.findAll();
       List<ProductDto> ProductDtos= new ArrayList<>();
       for(Product product:allProduct){
           ProductDtos.add(getProductDto(product));
       }
       return  ProductDtos;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
           Optional<Product> optionalProduct= productRepository.findById(productId);
           //throw an exception if product does not exists
          if(!optionalProduct.isPresent()){
              throw new Exception("product not present");
          }
      Product product=  optionalProduct.get();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setPrice((productDto.getPrice()));
        productRepository.save(product);
    }
    public Product findById(Integer productId) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("product id is invalid: " + productId);
        }
        return optionalProduct.get();
    }
}
