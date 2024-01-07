package info.nguyenanhdung.spingapi.demo.services;

import info.nguyenanhdung.spingapi.demo.dtos.CategoryDTO;
import info.nguyenanhdung.spingapi.demo.models.CategoryModel;
import info.nguyenanhdung.spingapi.demo.repositories.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final ICategoryRepository categoryRepository;

    // Khi khai bao notaion @RequiredArgsConstructor thi se ko can khai bao constructor
    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryModel createCategory(CategoryDTO categoryDTO) {
        CategoryModel newCategoryModel = CategoryModel
                .builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(newCategoryModel);
    }

    @Override
    public CategoryModel getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryModel updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        CategoryModel existingCategoryModel = getCategoryById(id);
        existingCategoryModel.setName(categoryDTO.getName());
        categoryRepository.save(existingCategoryModel);
        return existingCategoryModel;
    }

    @Override
    public void deleteCategory(Long id) {
        return categoryRepository.deleteById(id);
    }
}
