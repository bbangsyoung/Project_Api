package com.example.restroom.db;

import java.util.List;
import java.util.Optional;

public interface MomoryDbRepositoryIfs<T> {

    Optional<T> findById(int index); //기본 타입 엔티티 인덱스 반환
    T save(T entity); //저장 메소드
    void deleteById(int index);
    List<T> listAll();
}
