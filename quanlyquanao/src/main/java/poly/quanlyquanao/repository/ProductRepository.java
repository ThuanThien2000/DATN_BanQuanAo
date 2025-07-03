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
    
    
    @Query("SELECT p FROM Product p WHERE p.status = 1")
    List<Product> findAllActive();

    @Query("SELECT p FROM Product p WHERE p.status = 0")
    List<Product> findAllInactive();

    @Query("SELECT pd.product FROM ProductDetail pd WHERE pd.inventoryQuantity < :threshold")
    List<Product> findLowStockProducts(@Param("threshold") int threshold);
    
    @Query("SELECT DISTINCT p FROM Product p JOIN FETCH p.productDetails pd WHERE " +
    	       "(:categoryId IS NULL OR p.category.id = :categoryId) AND " +
    	       "(:minPrice IS NULL OR pd.price >= :minPrice) AND " +
    	       "(:maxPrice IS NULL OR pd.price <= :maxPrice) AND " +
    	       "(:size IS NULL OR pd.size = :size) AND (" +
    	       "(:styles IS NULL OR :styles = '') OR (" +
    	       "LOWER(pd.style) LIKE LOWER(CONCAT('%', :styles, '%'))" +
    	       "))")
    	List<Product> filterProducts(
    	        @Param("categoryId") Long categoryId,
    	        @Param("minPrice") Double minPrice,
    	        @Param("maxPrice") Double maxPrice,
    	        @Param("size") String size,
    	        @Param("styles") String styles,
    	        Pageable pageable);

}