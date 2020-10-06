package com.upgrad.Eshop;

import com.upgrad.Eshop.dao.*;
import com.upgrad.Eshop.entities.*;
import com.upgrad.Eshop.exception.*;
import com.upgrad.Eshop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class EshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
	}

}
