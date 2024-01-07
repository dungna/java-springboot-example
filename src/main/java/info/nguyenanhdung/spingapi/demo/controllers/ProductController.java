package info.nguyenanhdung.spingapi.demo.controllers;

import info.nguyenanhdung.spingapi.demo.models.ProductDTO;
import info.nguyenanhdung.spingapi.demo.dtos.ResponseObjectModel;
import info.nguyenanhdung.spingapi.demo.repositories.ProductRepository;
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
    private ProductRepository productRepository;
    @GetMapping("")
    List<ProductDTO> getAllProducts() {
        return productRepository.findAll();
    }
    @GetMapping("/{id}")
    // Let's return an object with: data, message, status
    ResponseEntity<ResponseObjectModel> findById(@PathVariable Long id) {
        Optional<ProductDTO> foundProduct = productRepository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObjectModel("ok", "Query product successfully", foundProduct)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObjectModel("false", "Cannot find product with id = "+id, "")
            );
    }
    @PostMapping("/insert")
    ResponseEntity<ResponseObjectModel> insertProduct(@RequestBody ProductDTO newProductDTO) {
        // 2 products must not have the same name
        List<ProductDTO> foundProductDTOS = productRepository.findByProductName(newProductDTO.getProductName().trim());
        if(foundProductDTOS.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObjectModel("failed", "Product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObjectModel("ok", "Insert Product successflly", productRepository.save(newProductDTO))
        );
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObjectModel> updateProduct(@RequestBody ProductDTO newProductDTO, @PathVariable Long id) {
        ProductDTO updatedProductDTO = productRepository.findById(id)
                .map(productDTO -> {
                    productDTO.setProductName(newProductDTO.getProductName());
                    productDTO.setYear(newProductDTO.getYear());
                    productDTO.setPrice(newProductDTO.getPrice());
                    return productRepository.save(productDTO);
                }).orElseGet(() -> {
                    newProductDTO.setId((id));
                    return productRepository.save(newProductDTO);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObjectModel("ok", "Update Product successflly", updatedProductDTO)
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObjectModel> deleteProduct(@PathVariable Long id) {
        boolean exists = productRepository.existsById(id);
        if(exists) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
              new ResponseObjectModel("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObjectModel("failed", "Cannot find product to delete", "")
        );
    }
}
