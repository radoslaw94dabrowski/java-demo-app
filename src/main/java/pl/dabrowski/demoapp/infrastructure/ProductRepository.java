package pl.dabrowski.demoapp.infrastructure;

import pl.dabrowski.demoapp.domain.Product;

import java.util.List;

public interface ProductRepository {
    void save(Product product);
    Product findById(String id);

    Product update(Product product, String name);

    void delete(String id);

    List<Product> getAll();
}
