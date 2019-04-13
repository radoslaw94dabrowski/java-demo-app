package pl.dabrowski.demoapp.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.dabrowski.demoapp.ProductExceptions;
import pl.dabrowski.demoapp.infrastructure.ProductRepository;

import java.time.LocalDateTime;
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
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice());

    }

    @Override
    public ProductsListResponseDto getAll() {
        List<Product> products = productRepository.getAll();
        return new ProductsListResponseDto(products.stream().map(product -> new ProductResponseDto(product.getId(), product.getName(), product.getPrice())).collect(Collectors.toList()));
    }

    @Override
    public ProductResponseDto create(ProductRequestDto productRequest) {
        if(!productRequest.isValid()){
            throw new RuntimeException("Product names cannot be empty!!");
        }
        String id =UUID.randomUUID().toString();
        LocalDateTime createAt = LocalDateTime.now();
        Product product = new Product(id, productRequest.getName(), createAt, productRequest.getPriceDto());
        // stworzyc produkt
        productRepository.save(product);

        ProductResponseDto responseDto = new ProductResponseDto(product.getId(), product.getName(), product.getPrice());
        //zapisac gdzies
        //przeemapowac Product na ProductResponse i zwrócić
        return responseDto;
    }

    @Override
    public ProductResponseDto update(String id, ProductRequestDto productRequestDto) {
        if(!productRequestDto.isValid()){
            throw new RuntimeException("Product names cannot be empty!!");
        }
        Product product = productRepository.findById(id);
        Product updateProduct = productRepository.update(product, productRequestDto.getName());

        return  new ProductResponseDto(updateProduct.getId(), updateProduct.getName(), updateProduct.getPrice());
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        productRepository.delete(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
