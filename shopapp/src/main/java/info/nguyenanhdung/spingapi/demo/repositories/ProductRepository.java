package info.nguyenanhdung.spingapi.demo.repositories;

import info.nguyenanhdung.spingapi.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);
}
