package info.nguyenanhdung.spingapi.demo.services;

import info.nguyenanhdung.spingapi.demo.dtos.ProductDTO;
import info.nguyenanhdung.spingapi.demo.exceptions.DataNotFoundException;
import info.nguyenanhdung.spingapi.demo.models.CategoryModel;
import info.nguyenanhdung.spingapi.demo.models.ProductModel;
import info.nguyenanhdung.spingapi.demo.repositories.ICategoryRepository;
import info.nguyenanhdung.spingapi.demo.repositories.IProductRepository;
import info.nguyenanhdung.spingapi.demo.responses.ProductResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductModel createProduct(ProductDTO productDTO) throws DataNotFoundException {
        CategoryModel existsCategory = categoryRepository.
                findById(productDTO.getCategoryId())
                .orElseThrow(() ->
                        new DataNotFoundException("Cannot find category with id: " + productDTO.getCategoryId()));
        ProductModel newProductModel = ProductModel.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .description(productDTO.getDescription())
                .category(existsCategory)
                .build();
        return productRepository.save(newProductModel);
    }

    @Override
    public ProductModel getProductById(long productId) throws Exception {
        Optional<ProductModel> optionalProduct = productRepository.getDetailProduct(productId);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new DataNotFoundException("Cannot find product with id =" + productId);
    }

    @Override
    public Page<ProductResponse> getAllProducts(PageRequest pageRequest){
        return productRepository
                .findAll(pageRequest)
                .map(ProductResponse::fromProduct);
    }

    @Override
    @Transactional
    public ProductModel updateProduct(long id, ProductDTO productDTO) throws Exception {
        ProductModel existingProductModel = getProductById(id);
        if(existingProductModel != null) {
            CategoryModel existingCategory = categoryRepository
                    .findById(productDTO.getCategoryId())
                    .orElseThrow(() ->
                            new DataNotFoundException(
                                    "Cannot find category with id: "+ productDTO.getCategoryId()));
            existingProductModel.setName(productDTO.getName());
            existingProductModel.setCategory(existingCategory);
            existingProductModel.setPrice(productDTO.getPrice());
            existingProductModel.setDescription(productDTO.getDescription());
            existingProductModel.setThumbnail(productDTO.getThumbnail());
            return productRepository.save(existingProductModel);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteProduct(long id) {
        Optional<ProductModel> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(productRepository::delete);
    }

    @Override
    @Transactional
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
}
