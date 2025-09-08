
package poly.quanlyquanao.service;
import poly.quanlyquanao.dto.CategoryDTO;

import poly.quanlyquanao.model.Category;

import java.util.List;

public interface CategoryService {
    Category save(Category category);
    Category update(Long id, Category category);
    void delete(Long id);
    Category getById(Long id);
    List<Category> getAll();
    List<CategoryDTO> getAllDTO();
    List<Category> getByStatus(int status);
}