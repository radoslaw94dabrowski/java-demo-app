package pl.dabrowski.demoapp.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Product {
    private final String id;
    private final String name;
    private final LocalDateTime createAt;
    private final PriceDto price;

    public Product(String id, String name, LocalDateTime createAt, PriceDto price) {
        this.id = id;
        this.name = name;
        this.createAt = createAt;
        this.price = price;
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


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createAt=" + createAt +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(createAt, product.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createAt);
    }
}
