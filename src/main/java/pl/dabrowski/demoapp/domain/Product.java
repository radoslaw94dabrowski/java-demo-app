package pl.dabrowski.demoapp.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public final class Product {
    private final String id;
    private final String name;
    private final LocalDateTime createAt;
    private final PriceDto price;
    private final ImageDto image;
    private final DescriptionDto description;
    private final List<TagsDto> tags;

    public Product(String id, String name, LocalDateTime createdAt, PriceDto price, ImageDto image, DescriptionDto description, List<TagsDto> tags) {
        this.id = id;
        this.name = name;
        this.createAt = createdAt;
        this.price = price;
        this.image = image;
        this.description = description;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public PriceDto getPrice() {
        return price;
    }

    public ImageDto getImage() {
        return image;
    }

    public DescriptionDto getDescription() {
        return description;
    }

    public List<TagsDto> getTags(){
        return tags;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createAt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(createAt, product.createAt) &&
                Objects.equals(price, product.price)&&
                Objects.equals(image, product.image)&&
                Objects.equals(description, product.description)&&
                Objects.equals(tags, product.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createAt, price, image, description, tags);
    }
}
