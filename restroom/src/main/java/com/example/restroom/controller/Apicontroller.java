package com.example.restroom.controller;

import com.example.restroom.wishlist.dto.WishListDto;
import com.example.restroom.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restroom")
@RequiredArgsConstructor
public class Apicontroller {

    private final WishListService wishListService;


    @GetMapping("search")
    public WishListDto search(@RequestParam String query) {
        return wishListService.search(query);



    }
}

