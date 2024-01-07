package info.nguyenanhdung.spingapi.demo.services;

import info.nguyenanhdung.spingapi.demo.dtos.ProductDTO;
import info.nguyenanhdung.spingapi.demo.models.ProductModel;
import info.nguyenanhdung.spingapi.demo.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IProductService {
    ProductModel createProduct(ProductDTO productDTO) throws Exception;
    ProductModel getProductById(long id) throws Exception;
    Page<ProductResponse> getAllProducts(PageRequest pageRequest);

    Page<ProductResponse> getAllProducts(String keyword,
                                         Long categoryId, PageRequest pageRequest);

    ProductModel updateProduct(long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(long id);
    boolean existsByName(String name);
}
