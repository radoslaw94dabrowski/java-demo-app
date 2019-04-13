package pl.dabrowski.demoapp.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductsListResponseDto {
    private final List<ProductResponseDto> products;
    @JsonCreator
    public ProductsListResponseDto(@JsonProperty("products") List<ProductResponseDto> products) {
        this.products = products;
    }

    public List<ProductResponseDto> getProducts() {
        return products;
    }
}
