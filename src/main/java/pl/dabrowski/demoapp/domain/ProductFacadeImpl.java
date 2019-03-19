package pl.dabrowski.demoapp.domain;

import org.springframework.stereotype.Component;
import pl.dabrowski.demoapp.infrastructure.ProductRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
class ProductFacadeImpl implements ProductFacade{

    public final ProductRepository productRepository;

    ProductFacadeImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto create(ProductRequestDto productRequest) {
        if(!productRequest.isValid()){
            throw new RuntimeException("Product names cannot be empty!!");
        }
        String id =UUID.randomUUID().toString();
        LocalDateTime createAt = LocalDateTime.now();
        Product product = new Product(id, productRequest.getName(), createAt);
        // stworzyc produkt
        productRepository.save(product);

        ProductResponseDto responseDto = new ProductResponseDto(product.getId(), product.getName());
        //zapisac gdzies
        //przeemapowac Product na ProductResponse i zwrócić
        return null;
    }
}
