package com.example.restroom.wishlist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListServiceTest {

    @Autowired
    private WishListService wishListService;

    @Test
    @DisplayName("검색테스트_로컬->이미지")
    public void searchTest() {
        var result = wishListService.search("고양이카페");
        System.out.println(result);
        Assertions.assertNotNull(result);
    }

}
