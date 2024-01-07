package info.nguyenanhdung.spingapi.demo.repositories;

import info.nguyenanhdung.spingapi.demo.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
