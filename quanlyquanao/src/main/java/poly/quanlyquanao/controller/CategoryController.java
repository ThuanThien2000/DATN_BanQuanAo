package poly.quanlyquanao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.Category;
import poly.quanlyquanao.service.CategoryService;

import java.util.List;
import poly.quanlyquanao.dto.CategoryDTO;
@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    //tạo danh mục
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.save(category));
    }

    //lấy all danh mục có trong dữ liệu localhost:8080/api/category/all
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAllDTO());
    }
    
    //lấy danh mục theo id localhost:8080/api/category/(id)
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }
    
    //sửa danh mục localhost:8080/api/category/update/(id muốn sửa)
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Category updated = categoryService.update(id, category);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    
    //xóa danh mục chuyển thành hết hàng localhost:8080/api/category/delete(id muốn xóa)
    @DeleteMapping("/delete/{id}") 
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        category.setStatus(0); // Chuyển status về 0 để soft delete
        categoryService.save(category); // Lưu lại thay đổi
        return ResponseEntity.noContent().build();
    }
    
    //tìm danh mục theo trạng thái hết hay còn hàng
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Category>> getByStatus(@PathVariable int status) {
        return ResponseEntity.ok(categoryService.getByStatus(status));
    }
}