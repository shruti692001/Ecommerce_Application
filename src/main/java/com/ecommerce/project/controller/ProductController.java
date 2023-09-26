package com.ecommerce.project.controller;

import com.ecommerce.project.common.ApiResponse;
import com.ecommerce.project.dto.ProductDto;
import com.ecommerce.project.module.Category;
import com.ecommerce.project.repository.CategoryRepo;
import com.ecommerce.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepo categoryRepo;
  @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct( @RequestBody ProductDto productDto) {

      Optional<Category>  optionalCategory=categoryRepo.findById(productDto.getCategoryId());
          if(!optionalCategory.isPresent()){
              return new ResponseEntity<>(new ApiResponse(false,"category does not exist"), HttpStatus.BAD_REQUEST);
          }
           productService.createProduct(productDto,optionalCategory.get());
          return new ResponseEntity<>(new ApiResponse(true,"product has been added"), HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProduct(){
      List<ProductDto> products = productService.getAllproducts();
      return new ResponseEntity<>(products,HttpStatus.OK);
    }
   //edit
    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct( @PathVariable("productId") Integer productId,@RequestBody ProductDto productDto) throws Exception {

        Optional<Category>  optionalCategory=categoryRepo.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"category does not exist"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto,productId);
        return new ResponseEntity<>(new ApiResponse(true,"product has been updated"), HttpStatus.OK);
    }
}


