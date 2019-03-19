package pl.dabrowski.demoapp.infrastructure;

import pl.dabrowski.demoapp.domain.Product;

public interface ProductRepository {
    void save(Product product);
}
