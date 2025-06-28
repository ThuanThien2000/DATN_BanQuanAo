package poly.quanlyquanao.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.quanlyquanao.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);
}