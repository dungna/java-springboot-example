package info.nguyenanhdung.spingapi.demo.controllers;

import info.nguyenanhdung.spingapi.demo.models.Product;
import info.nguyenanhdung.spingapi.demo.models.ResponseObject;
import info.nguyenanhdung.spingapi.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Productions")
public class ProductController {
    // DI = Dependency Injection
    @Autowired
    private ProductRepository repository;

    @GetMapping("/getAllProducts")
    List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Get detail product
    @GetMapping("/{id}")
    // Let's return an object with: data, message, status
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query product successfully", foundProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Cannot find product with id = "+id, "")
            );
    }

    // Insert data
    //Postman: Raw, JSON
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        // 2 products must not have the same name
        List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
        if(foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Product successflly", repository.save(newProduct))
        );
    }
    // Update, upsert = update if found, otherwise insert
    @PutMapping("/{id")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updatedProduct = repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setYear(newProduct.getYear());
                    product.setPrice(newProduct.getPrice());
                    return repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId((id));
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update Product successflly", updatedProduct)
        );
    }

    // Delete a Product => DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if(exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
              new ResponseObject("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find product to delete", "")
        );
    }
}
