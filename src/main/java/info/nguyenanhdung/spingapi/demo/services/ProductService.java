package info.nguyenanhdung.spingapi.demo.services;

import info.nguyenanhdung.shopapp.dtos.ProductDTO;
import info.nguyenanhdung.shopapp.dtos.ProductImageDTO;
import info.nguyenanhdung.shopapp.exceptions.DataNotFoundException;
import info.nguyenanhdung.shopapp.exceptions.InvalidParamException;
import info.nguyenanhdung.shopapp.models.ProductImageModel;
import info.nguyenanhdung.shopapp.models.ProductModel;
import info.nguyenanhdung.shopapp.repositories.ICategoryRepository;
import info.nguyenanhdung.shopapp.repositories.IProductImageRepository;
import info.nguyenanhdung.shopapp.repositories.IProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private final IProductImageRepository productImageRepository;

    @Override
    @Transactional
    public ProductModel createProduct(ProductDTO productDTO) throws DataNotFoundException {
        Category  existsCategory = categoryRepository.
                findById(productDTO.getCategoryId)
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
    public Page<ProductResponse> getAllProducts(String keyword,
                                                Long categoryId, PageRequest pageRequest) {
        // Lấy danh sách sản phẩm theo trang (page), giới hạn (limit), và categoryId (nếu có)
        Page<ProductModel> productsPage;
        productsPage = productRepository.searchProducts(categoryId, keyword, pageRequest);
        return productsPage.map(ProductResponse::fromProduct);
    }

    @Override
    @Transactional
    public ProductModel updateProduct(long id, ProductDTO productDTO) throws Exception {
        ProductModel existingProductModel = getProductById(id);
        if(existingProductModel != null) {
            //copy các thuộc tính từ DTO -> Product
            //Có thể sử dụng ModelMapper
            Category existingCategory = categoryRepository
                    .findById(productDTO.getCategoryId())
                    .orElseThrow(() ->
                            new DataNotFoundException(
                                    "Cannot find category with id: "+productDTO.getCategoryId()));
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

    @Override
    @Transactional
    public ProductImageModel createProductImage(
            Long productId,
            ProductImageDTO productImageDTO) throws Exception {
        ProductModel existingProductModel = productRepository
                .findById(productId)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Cannot find product with id: "+productImageDTO.getProductId()));
        ProductImageModel newProductImageModel = ProductImageModel.builder()
                .product(existingProductModel)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        //Ko cho insert quá 5 ảnh cho 1 sản phẩm
        int size = productImageRepository.findByProductId(productId).size();
        if(size >= ProductImageModel.MAXIMUM_IMAGES_PER_PRODUCT) {
            throw new InvalidParamException(
                    "Number of images must be <= "
                            + ProductImageModel.MAXIMUM_IMAGES_PER_PRODUCT);
        }
        return productImageRepository.save(newProductImageModel);
    }
}
