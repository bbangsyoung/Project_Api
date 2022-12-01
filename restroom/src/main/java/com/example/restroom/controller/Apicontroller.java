package com.example.restroom.controller;

import com.example.restroom.wishlist.dto.WishListDto;
import com.example.restroom.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/restroom")
@RequiredArgsConstructor
public class Apicontroller {

    private final WishListService wishListService;


    @GetMapping("search")
    public WishListDto search(@RequestParam String query) {
        return wishListService.search(query);

    }

    @PostMapping("")
    public WishListDto add(@RequestBody WishListDto wishListDto) {
        log.info("{}", wishListDto);

        return wishListService.add(wishListDto);

    }

    //전체리스트 출력
    @GetMapping("/all")
    public List<WishListDto> findAll() {
        return wishListService.findAll();

    }
}

