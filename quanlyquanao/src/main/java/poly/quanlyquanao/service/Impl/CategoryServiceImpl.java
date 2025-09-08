
package poly.quanlyquanao.service.Impl;

import poly.quanlyquanao.dto.CategoryDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.Category;
import poly.quanlyquanao.repository.CategoryRepository;
import poly.quanlyquanao.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<CategoryDTO> getAllDTO() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> new CategoryDTO(
            category.getId(),
            category.getCategoryName()
        )).toList();
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setStatus(1); // Set default status    
        return categoryRepository.save(category);
    }
    @Override
    public List<CategoryDTO> getByStatus(int status) {
        List<Category> categories = categoryRepository.findByStatus(status);
        return categories.stream().map(category -> new CategoryDTO(
            category.getId(),
            category.getCategoryName()
        )).toList();
    }
    
    @Override
    public Category update(Long id, CategoryDTO categoryDTO) {
        Optional<Category> existing = categoryRepository.findById(id);
        if (existing.isPresent()) {
            Category existingCategory = existing.get();
            existingCategory.setCategoryName(categoryDTO.getCategoryName());
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setStatus(0);
            categoryRepository.save(category);
        }
    }

    @Override
    public CategoryDTO getById(Long id) {
        return categoryRepository.findById(id).map(category -> new CategoryDTO(
            category.getId(),
            category.getCategoryName()
        )).orElse(null);
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream().map(category -> new CategoryDTO(
            category.getId(),
            category.getCategoryName()
        )).toList();
    }
}