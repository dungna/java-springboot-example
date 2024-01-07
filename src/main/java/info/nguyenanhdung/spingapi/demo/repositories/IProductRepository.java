package info.nguyenanhdung.spingapi.demo.repositories;

import info.nguyenanhdung.spingapi.demo.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findByProductName(String productName);
}
