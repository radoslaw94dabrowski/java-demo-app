package pl.dabrowski.demoapp.domain;

import org.springframework.http.ResponseEntity;

public interface ProductFacade {
    //get
    ProductResponseDto findByID(String id);
    //getAll
    ProductsListResponseDto getAll();


    ProductsListResponseDto getAllByTag(String tag);
    //create
    ProductResponseDto create(ProductRequestDto productRequest);
    //update
    ProductResponseDto update(String id, ProductRequestDto productRequestDto);
    //delete
    ResponseEntity<Void> delete(String id);


}
