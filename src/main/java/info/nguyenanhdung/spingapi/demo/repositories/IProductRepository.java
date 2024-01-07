package info.nguyenanhdung.spingapi.demo.repositories;

import info.nguyenanhdung.spingapi.demo.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IProductRepository extends JpaRepository<ProductModel, Long> {
    boolean existsByName(String name);
    Page<ProductModel> findAll(Pageable pageable);//phân trang
}
