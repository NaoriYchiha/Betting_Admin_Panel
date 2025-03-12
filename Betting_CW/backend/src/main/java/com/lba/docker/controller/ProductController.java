package com.lba.docker.controller;

import com.lba.docker.entity.Product;
import com.lba.docker.repository.ProductRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductRepository repository;


    public ProductController(ProductRepository productRepository){
        this.repository = productRepository;
    }

    @GetMapping
    public List<Product> getAll(){

        return repository.findAll();
    }
}
