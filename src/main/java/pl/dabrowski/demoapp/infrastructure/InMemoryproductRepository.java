package pl.dabrowski.demoapp.infrastructure;

import org.springframework.stereotype.Component;
import pl.dabrowski.demoapp.domain.Product;

import java.util.HashMap;
import java.util.Map;
@Component
class InMemoryproductRepository implements ProductRepository{

    private final Map<String, Product> products= new HashMap<>();

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }
}
