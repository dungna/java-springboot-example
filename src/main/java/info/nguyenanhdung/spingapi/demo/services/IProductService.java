package info.nguyenanhdung.spingapi.demo.services;

import info.nguyenanhdung.shopapp.dtos.ProductDTO;
import info.nguyenanhdung.shopapp.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IProductService {
    public ProductModel createProduct(ProductDTO productDTO) throws Exception;
    ProductModel getProductById(long id) throws Exception;
    Page<ProductModel> getAllProducts(String keyword, Long categoryId, PageRequest pageRequest);
    ProductModel updateProduct(long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(long id);
    boolean existsByName(String name);
    ProductImage createProductImage(Long productId, ProductImageDTO productImageDTO) throws Exception;
}
