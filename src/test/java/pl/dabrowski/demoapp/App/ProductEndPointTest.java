package pl.dabrowski.demoapp.App;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import pl.dabrowski.demoapp.DemoappApplicationTests;
import pl.dabrowski.demoapp.domain.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ProductEndPointTest extends DemoappApplicationTests {
    @Autowired
    ProductFacade productFacade;
    List<TagsDto> exampleTags = List.of(new TagsDto("hot"), new TagsDto("cold"));

    @Test
    public void ShouldGetExistProduct() {
        //given
        ProductRequestDto request = new ProductRequestDto("product", new PriceDto("20.00", "PLN"),  new ImageDto(""), new DescriptionDto("new product"), exampleTags);
        ProductResponseDto existProduct = productFacade.create(request);
        final String url = "http://localhost:" + port + "/products/" +"product-id-" +existProduct.getId();

        //when
        ResponseEntity<ProductResponseDto> result = httpClient.getForEntity(url, ProductResponseDto.class);
        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualToComparingOnlyGivenFields(existProduct);
    }
    @Test
    public void ShouldGetALLExistProduct() {
        //given
        ProductRequestDto request1 = new ProductRequestDto("product1", new PriceDto("20.00", "PLN"),  new ImageDto(""), new DescriptionDto("new product"), exampleTags);
        productFacade.create(request1);
        ProductRequestDto request2 = new ProductRequestDto("product1", new PriceDto("20.00", "PLN"),  new ImageDto(""), new DescriptionDto("new product"), exampleTags);
        productFacade.create(request2);

        final String url = "http://localhost:" + port + "/products";

        //when
        ResponseEntity<ProductsListResponseDto> result = httpClient.getForEntity(url, ProductsListResponseDto.class);
        ProductsListResponseDto products = result.getBody();
        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(products.getProducts().get(0).getName()).isEqualTo(productFacade.getAll().getProducts().get(0).getName());
        assertThat(products.getProducts().get(1).getName()).isEqualTo(productFacade.getAll().getProducts().get(1).getName());
    }

    @Test
    public void ShouldReturn404IfNotExist() {
        ProductRequestDto request = new ProductRequestDto("product", new PriceDto("20.00", "PLN"),  new ImageDto(""), new DescriptionDto("new product"), exampleTags);
        final String url = "http://localhost:" + port + "/products/"+"product-id-" + "123";

        ResponseEntity<ProductResponseDto> result = httpClient.getForEntity(url, ProductResponseDto.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(404);

    }

    @Test
    public void ShouldCreateProducts() {
        //given (input)
        final String url = "http://localhost:" + port + "/products";
        final ProductRequestDto product = new ProductRequestDto("iphone", new PriceDto("20.00", "PLN"),  new ImageDto(""), new DescriptionDto("new product"), exampleTags);
        String productjson = mapToJson(product);
        //when (to co testujemy)
        ResponseEntity<ProductResponseDto> result = httpClient.postForEntity(url, getHttpRequest(productjson), ProductResponseDto.class);

        //then (output)
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getName()).isEqualTo("iphone");
    }

    @Test
    public void ShouldUpdateProduct() {
        ProductRequestDto request =new ProductRequestDto("product", new PriceDto("20.00", "PLN"),  new ImageDto(""), new DescriptionDto("new product"), exampleTags);
        ProductResponseDto existProduct = productFacade.create(request);
        final String url = "http://localhost:" + port + "/products/"+ existProduct.getId();

        ProductRequestDto updateproduct = new ProductRequestDto("update product", new PriceDto("20.00", "PLN"),  new ImageDto(""), new DescriptionDto("new product"), exampleTags);

        String productJson = mapToJson(updateproduct);

        ResponseEntity<ProductResponseDto> result = httpClient.exchange(url, HttpMethod.PUT, getHttpRequest(productJson), ProductResponseDto.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getId()).isEqualTo(existProduct.getId());
        assertThat(result.getBody().getName()).isEqualTo(updateproduct.getName());
    }
    @Test
    public void ShouldDeleteProduct(){
        ProductRequestDto request = new ProductRequestDto("product", new PriceDto("20.00", "PLN"),  new ImageDto(""), new DescriptionDto("new product"), exampleTags);
        ProductResponseDto existProduct = productFacade.create(request);
        final String url = "http://localhost:" + port + "/products/" + existProduct.getId();

        ResponseEntity<Void> result = httpClient.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(204);
    }

    String mapToJson(ProductRequestDto productRequestDto) {
        try {
            return objectMapper.writeValueAsString(productRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private HttpEntity<String> getHttpRequest(String json) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type", "application/json");
        return new HttpEntity<>(json, httpHeaders);
    }
}
