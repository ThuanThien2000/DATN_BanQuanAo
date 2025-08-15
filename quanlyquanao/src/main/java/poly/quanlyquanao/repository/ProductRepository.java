package poly.quanlyquanao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import poly.quanlyquanao.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductCode(String productCode);
    
    @Query("SELECT p FROM Product p WHERE p.status = 1 ORDER BY p.id DESC")
    List<Product> findAllActive();

    // @Query("SELECT p FROM Product p WHERE p.status = 0")
    // List<Product> findAllInactive();

    @Query("SELECT pd.product FROM ProductDetail pd WHERE pd.inventoryQuantity < :threshold")
    List<Product> findLowStockProducts(@Param("threshold") int threshold);

    @Query("SELECT p FROM Product p WHERE p.isFeatured = true AND p.status = 1")
    List<Product> findFeaturedProducts();   
    @Query("SELECT COUNT(p) FROM Product p WHERE p.isFeatured = true AND p.status = 1")
    long countFeaturedProducts();   
    
    List<Product> findTop4ByCategory_IdAndStatusOrderByIdDesc(Long categoryId, Integer status);

}