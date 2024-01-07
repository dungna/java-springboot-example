package info.nguyenanhdung.spingapi.demo.controllers;

import info.nguyenanhdung.spingapi.demo.models.ProductModel;
import info.nguyenanhdung.spingapi.demo.models.ResponseObjectModel;
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
    List<ProductModel> getAllProducts() {
        return repository.findAll();
    }

    // Get detail product
    @GetMapping("/{id}")
    // Let's return an object with: data, message, status
    ResponseEntity<ResponseObjectModel> findById(@PathVariable Long id) {
        Optional<ProductModel> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObjectModel("ok", "Query product successfully", foundProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObjectModel("false", "Cannot find product with id = "+id, "")
            );
    }

    // Insert data
    //Postman: Raw, JSON
    @PostMapping("/insert")
    ResponseEntity<ResponseObjectModel> insertProduct(@RequestBody ProductModel newProductModel) {
        // 2 products must not have the same name
        List<ProductModel> foundProductModels = repository.findByProductName(newProductModel.getProductName().trim());
        if(foundProductModels.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObjectModel("failed", "Product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObjectModel("ok", "Insert Product successflly", repository.save(newProductModel))
        );
    }
    // Update, upsert = update if found, otherwise insert
    @PutMapping("/{id")
    ResponseEntity<ResponseObjectModel> updateProduct(@RequestBody ProductModel newProductModel, @PathVariable Long id) {
        ProductModel updatedProductModel = repository.findById(id)
                .map(productModel -> {
                    productModel.setProductName(newProductModel.getProductName());
                    productModel.setYear(newProductModel.getYear());
                    productModel.setPrice(newProductModel.getPrice());
                    return repository.save(productModel);
                }).orElseGet(() -> {
                    newProductModel.setId((id));
                    return repository.save(newProductModel);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObjectModel("ok", "Update Product successflly", updatedProductModel)
        );
    }

    // Delete a Product => DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObjectModel> deleteProduct(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if(exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
              new ResponseObjectModel("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObjectModel("failed", "Cannot find product to delete", "")
        );
    }
}
