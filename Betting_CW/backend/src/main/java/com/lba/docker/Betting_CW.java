package com.lba.docker;

import com.lba.docker.entity.Product;
import com.lba.docker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Betting_CW {


    public static void main(String[] args) {
        SpringApplication.run(Betting_CW.class, args);
    }

}
