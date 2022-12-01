package com.example.restroom.wishlist.service;

import com.example.restroom.naver.NaverClient;
import com.example.restroom.naver.dto.SearchImageReq;
import com.example.restroom.naver.dto.SearchLocalReq;
import com.example.restroom.wishlist.Entity.WishListEntity;
import com.example.restroom.wishlist.dto.WishListDto;
import com.example.restroom.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

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
                result.setRoadAddress(localItem.getRoadAdress());
                result.setImageLink(imageItem.getLink());

                return result;
            }
        }

        return new WishListDto();


    }


    public WishListDto add(WishListDto wishListDto) {
        var entity = dtoToEntity(wishListDto);

        var saveEntity = wishListRepository.save(entity);
        return entityToDto(saveEntity);
    }

    private WishListEntity dtoToEntity(WishListDto wishListDto) {
        var entity = new WishListEntity();
        entity.setIndex(wishListDto.getIndex());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());
        return entity;
    }

    private WishListDto entityToDto(WishListEntity wishListEntity) {
        var dto = new WishListDto();
        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());
        return dto;
    }

    public List<WishListDto> findAll() {


        return wishListRepository.listAll()
                .stream()
                .map(it -> entityToDto(it))
                .collect(Collectors.toList());
    }
}
