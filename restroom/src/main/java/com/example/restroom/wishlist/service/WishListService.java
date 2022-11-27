package com.example.restroom.wishlist.service;

import com.example.restroom.naver.NaverClient;
import com.example.restroom.naver.dto.SearchImageReq;
import com.example.restroom.naver.dto.SearchLocalReq;
import com.example.restroom.wishlist.dto.WishListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;

    public WishListDto search(String query) {
        //지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var SearchLocalRes = naverClient.searchLocal(searchLocalReq);

        //데이터가 있을지 없을지 모르기 때문에 일단은 무조건 있다는 가정으로 if선언
        if(SearchLocalRes.getTotal() > 0) {
            var localItem = SearchLocalRes.getItems().stream().findFirst().get(); //무조건 존재

            //띄어쓰기나 괄호 등이 있으면 모두 제외. 검색하기 용이하도록 필터링해주는 정규표현식
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>","");



            //**지역검색이 있을 때만 이미지검색!!!
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            var searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal() > 0) {
                var imageItem = searchImageRes.getItems().stream().findFirst().get();
                //결과리턴
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory((localItem.getCategory()));
                result.setAddress(localItem.getAddress());
                result.setReadAddress(localItem.getRoadAdress());
                result.setImageLink(imageItem.getLink());

                return result;
            }
        }

        return new WishListDto();


    }



}
