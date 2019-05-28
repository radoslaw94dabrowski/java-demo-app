package pl.dabrowski.demoapp.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dabrowski.demoapp.domain.*;

import java.awt.*;

@RestController
@RequestMapping("/products")
class ProductEndpoint {
    private final ProductFacade productFacade;

    @Autowired
    ProductEndpoint(ProductFacade productFacade)
    {
        this.productFacade = productFacade;
    }
    @PostMapping
    ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto){
        return productFacade.create(productRequestDto);
    }
    @CrossOrigin
    @GetMapping(path = "/product-id-{id}")/*, method = RequestMethod.GET produces = MediaType.*/
    ProductResponseDto getProduct(@PathVariable("id") String id){
        return productFacade.findByID(id);
    }
    @CrossOrigin
    @GetMapping
    ResponseEntity<ProductsListResponseDto> getAllProducts(@RequestParam(value="tag", required=false) String tag){
        if(tag != null && !tag.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(productFacade.getAllByTag(tag));
        }
        return ResponseEntity.status(HttpStatus.OK).body(productFacade.getAll());
    }

    @PutMapping("/{id}")
    ProductResponseDto updateProduct(@PathVariable("id") String id, @RequestBody ProductRequestDto productRequestDto){
        return  productFacade.update(id, productRequestDto);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteproduct(@PathVariable("id") String id){
        return productFacade.delete(id);
    }
    //Komentarz do commit - usunąć
}
