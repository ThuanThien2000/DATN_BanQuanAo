
package poly.quanlyquanao.service;
import poly.quanlyquanao.dto.CategoryDTO;

import poly.quanlyquanao.model.Category;

import java.util.List;

public interface CategoryService {
    Category save(CategoryDTO category);
    Category update(Long id, CategoryDTO category);
    void delete(Long id);
    CategoryDTO getById(Long id);
    List<CategoryDTO> getAll();
    List<CategoryDTO> getAllDTO();
    List<CategoryDTO> getByStatus(int status);
}