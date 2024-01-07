package info.nguyenanhdung.spingapi.demo.controllers;

import info.nguyenanhdung.spingapi.demo.models.ProductModel;
import info.nguyenanhdung.spingapi.demo.dtos.ProductDTO;
import info.nguyenanhdung.spingapi.demo.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${api.prefix}/product")
public class ProductController {
    // DI = Dependency Injection
    @Autowired
    private IProductRepository IProductRepository;
    @GetMapping("")
    List<ProductModel> getAllProducts() {
        return IProductRepository.findAll();
    }
    @GetMapping("/{id}")
    // Let's return an object with: data, message, status
    ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        Optional<ProductModel> foundProduct = IProductRepository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ProductDTO("ok", "Query product successfully", foundProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ProductDTO("false", "Cannot find product with id = "+id, "")
            );
    }
    @PostMapping("/insert")
    ResponseEntity<ProductDTO> insertProduct(@RequestBody ProductModel newProductModel) {
        // 2 products must not have the same name
        List<ProductModel> foundProductModels = IProductRepository.findByProductName(newProductModel.getProductName().trim());
        if(foundProductModels.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ProductDTO("failed", "Product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ProductDTO("ok", "Insert Product successflly", IProductRepository.save(newProductModel))
        );
    }
    @PutMapping("/{id}")
    ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductModel newProductModel, @PathVariable Long id) {
        ProductModel updatedProductModel = IProductRepository.findById(id)
                .map(productModel -> {
                    productModel.setProductName(newProductModel.getProductName());
                    productModel.setYear(newProductModel.getYear());
                    productModel.setPrice(newProductModel.getPrice());
                    return IProductRepository.save(productModel);
                }).orElseGet(() -> {
                    newProductModel.setId((id));
                    return IProductRepository.save(newProductModel);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ProductDTO("ok", "Update Product successflly", updatedProductModel)
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) {
        boolean exists = IProductRepository.existsById(id);
        if(exists) {
            IProductRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
              new ProductDTO("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ProductDTO("failed", "Cannot find product to delete", "")
        );
    }
}
