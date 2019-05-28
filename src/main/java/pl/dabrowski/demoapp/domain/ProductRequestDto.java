package pl.dabrowski.demoapp.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//Ignoruje nieznane wartości
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequestDto {
    private final String name;
    private final PriceDto price;
    private final ImageDto image;
    private final DescriptionDto description;
    private final List<TagsDto> tags;

    @JsonCreator
    public ProductRequestDto(@JsonProperty("name") String name,
                             @JsonProperty("price") PriceDto price,
                             @JsonProperty("image") ImageDto image,
                             @JsonProperty("description") DescriptionDto description,
                             @JsonProperty("tags") List<TagsDto> tags) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.tags = tags;
    }

    public  boolean isValid(){
        return  name != null && !name.isBlank();
    }

    public String getName() {
        return name;
    }

    public PriceDto getPrice(){
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
        return "ProductRequestDto{" +
                "name='" + name + '\'' +
                "price='" + price + '\'' +
                "description='" + description + '\'' +
                "image='" + image + '\'' +
                "tags='" + tags + '\'' +
                '}';
    }
}

