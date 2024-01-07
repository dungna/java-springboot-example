package info.nguyenanhdung.spingapi.demo.services;

import info.nguyenanhdung.shopapp.dtos.CategoryDTO;
import info.nguyenanhdung.shopapp.models.CategoryModel;

import java.util.List;

public interface ICategoryService {
    CategoryModel createCategory(CategoryDTO category);
    CategoryModel getCategoryById(Long id);
    List<CategoryModel> getAllCategories();
    CategoryModel updateCategory(Long categoryId, CategoryDTO category);
    void deleteCategory(Long id);
}
