package com.example.restroom;

import com.example.restroom.wishlist.dto.WishListDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootApplication
public class RestroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestroomApplication.class, args);
	}



}
