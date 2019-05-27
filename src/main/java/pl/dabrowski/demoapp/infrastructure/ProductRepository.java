package pl.dabrowski.demoapp.infrastructure;

import pl.dabrowski.demoapp.domain.*;

import java.util.List;

public interface ProductRepository {
    void save(Product product);
    Product findById(String id);
    List<Product> getAllByTags(String tag);
    Product update(Product product, String name, PriceDto price, ImageDto image, DescriptionDto description, List<TagsDto> tags);


    void delete(String id);

    List<Product> getAll();
}
