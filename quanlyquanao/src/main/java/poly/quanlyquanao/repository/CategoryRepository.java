package poly.quanlyquanao.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.quanlyquanao.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);
    @Query("SELECT c FROM Category c WHERE c.status = 1")
    List<Category> getAllActive();
    @Query("SELECT c FROM Category c WHERE c.status = :status")
    List<Category> findByStatus(@Param("status") int status);
}