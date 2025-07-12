
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
        List<Category> categories = getAll();
        return categories.stream().map(category -> new CategoryDTO(
            category.getId(),
            category.getCategoryName()
        )).toList();
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public List<Category> getByStatus(int status) {
        return categoryRepository.findByStatus(status);
    }
    
    @Override
    public Category update(Long id, Category category) {
        Optional<Category> existing = categoryRepository.findById(id);
        if (existing.isPresent()) {
            Category existingCategory = existing.get();
            existingCategory.setCategoryName(category.getCategoryName());
            existingCategory.setStatus(category.getStatus());
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
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}