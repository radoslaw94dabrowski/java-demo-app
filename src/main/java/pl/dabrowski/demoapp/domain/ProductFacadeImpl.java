package pl.dabrowski.demoapp.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.dabrowski.demoapp.infrastructure.ProductRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
class ProductFacadeImpl implements ProductFacade{

    public final ProductRepository productRepository;

    ProductFacadeImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto findByID(String id) {
        Product product = productRepository.findById(id);
        if(product.equals(null)){
            throw new RuntimeException("Product not exist!!");
        }
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImage(), product.getDescription(), product.getTags());
    }

    @Override
    public ProductsListResponseDto getAll() {
        List<Product> products = productRepository.getAll();
        return new ProductsListResponseDto(products.stream().map(product ->
                new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImage(), product.getDescription(), product.getTags())).sorted(Comparator.comparing(ProductResponseDto::getId)).collect(Collectors.toList()));
    }

    @Override
    public ProductsListResponseDto getAllByTag(String tag) {
        List<Product> products = productRepository.getAllByTags(tag);
        return new ProductsListResponseDto(products.stream().map(product ->
                new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImage(), product.getDescription(), product.getTags())).sorted(Comparator.comparing(ProductResponseDto::getId)).collect(Collectors.toList()));
    }

    @Override
    public ProductResponseDto create(ProductRequestDto productRequest) {
        if(!productRequest.isValid()){
            throw new RuntimeException("Product names cannot be empty!!");
        }
        String id =UUID.randomUUID().toString();
        LocalDateTime createAt = LocalDateTime.now();
        Product product = new Product(id, productRequest.getName(), createAt, productRequest.getPrice(), productRequest.getImage(), productRequest.getDescription(), productRequest.getTags());
        // stworzyc produkt
        productRepository.save(product);

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImage(),
                product.getDescription(),
                product.getTags()
        );
    }

    @Override
    public ProductResponseDto update(String id, ProductRequestDto productRequest) {
        if(!productRequest.isValid()){ //da sie skrocic do 1 metody Strings
            throw new RuntimeException("Product cannot be update");
        }

        Product product = productRepository.findById(id);
        Product updatedProduct = productRepository.update(product, productRequest.getName(), productRequest.getPrice(), productRequest.getImage(), productRequest.getDescription(), productRequest.getTags());

        return new ProductResponseDto(updatedProduct.getId(), updatedProduct.getName(), updatedProduct.getPrice(), updatedProduct.getImage(), updatedProduct.getDescription(), updatedProduct.getTags());
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        productRepository.delete(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
