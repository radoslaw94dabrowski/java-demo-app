package pl.dabrowski.demoapp.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.dabrowski.demoapp.domain.ExceptionProductNotFound;
import pl.dabrowski.demoapp.domain.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component
@Repository
class InMemoryproductRepository implements ProductRepository {

    private final Map<String, Product> products = new HashMap<>();

    @Override
    public void save(Product product) {

        products.put(product.getId(), product);
    }

    @Override
    public Product findById(String id) {
        if(!products.containsKey(id)) throw new ExceptionProductNotFound("Produkt o podanym id nie istnieje");
        return products.get(id);
    }

    @Override
    public Product update(Product product, String name) {
        if(!products.containsKey(product.getId())) throw  new ExceptionProductNotFound("Wystąpił błąd podczas aktualizacji. Sprawdź, czy id produktu jest poprawne.");
        products.put(product.getId(), new Product(product.getId(), name, product.getCreateAt(), product.getPrice()));
        return products.get(product.getId());
    }


    @Override
    public void delete(String id) {
        if(!products.containsKey(id)) throw  new ExceptionProductNotFound("Wystąpił błąd podczas usuwania. Sprawdź, czy id produktu jest poprawne.");
        products.remove(id);
    }

    @Override
    public List<Product> getAll() {
        return  List.copyOf(products.values());
    }

}
