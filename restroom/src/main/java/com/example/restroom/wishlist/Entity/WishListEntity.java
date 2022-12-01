package com.example.restroom.wishlist.Entity;

import com.example.restroom.db.MemoryDbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListEntity extends MemoryDbEntity {

    private String title;                   //장소
    private String category;                //카테고리
    private String address;                 //주소
    private String roadAddress;             //도로명
    private String imageLink;               //이미지주소
    private boolean isVisit;                //방문여부
    private int visitCount;                 //방문횟수
    private LocalDateTime lastVisitDate;    //마지막방문일

}
