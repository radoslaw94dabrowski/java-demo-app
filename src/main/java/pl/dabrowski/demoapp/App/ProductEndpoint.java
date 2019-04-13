package pl.dabrowski.demoapp.App;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "/{id}")/*, method = RequestMethod.GET produces = MediaType.*/
    ProductResponseDto getProduct(@PathVariable("id") String id){
        return productFacade.findByID(id);
    }
    @GetMapping
    ProductsListResponseDto getAllProducts(){
        return productFacade.getAll();
    }
    @PutMapping("/{id}")
    ProductResponseDto updateProduct(@PathVariable String id, @RequestBody ProductRequestDto productRequestDto){
        return  productFacade.update(id, productRequestDto);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteproduct(@PathVariable String id){
        return productFacade.delete(id);
    }
}
