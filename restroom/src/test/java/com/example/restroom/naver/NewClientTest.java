package com.example.restroom.naver;

import com.example.restroom.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NewClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    @DisplayName("naver_검색연동테스트")
    public void searchLocalTest(){
        var search = new SearchLocalReq();
        search.setQuery("한대역화장실");

        var result = naverClient.searchLocal(search);
        System.out.println(result);


    }



}
