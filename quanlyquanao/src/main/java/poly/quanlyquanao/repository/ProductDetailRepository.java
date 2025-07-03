package poly.quanlyquanao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.model.ProductDetail;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    
    List<ProductDetail> findByProductId(Long productId);

    @Query("SELECT pd FROM ProductDetail pd WHERE pd.product.id = :productId AND pd.status = 1")
    List<ProductDetail> findActiveByProductId(Long productId);
    
    @Query("SELECT DISTINCT pd.product FROM ProductDetail pd " +
    	       "WHERE (:minPrice IS NULL OR pd.price >= :minPrice) " +
    	       "AND (:maxPrice IS NULL OR pd.price <= :maxPrice) " +
    	       "AND (:sizes IS NULL OR pd.size IN :sizes) " +
    	       "AND (" +
    	           ":styles IS NULL OR " +
    	           "EXISTS (SELECT pd2 FROM ProductDetail pd2 WHERE pd2.product = pd.product AND LOWER(pd2.style) LIKE LOWER(CONCAT('%', :styleMatch, '%')))" +
    	       ")")
    	Page<Product> filterProducts(
    	        @Param("minPrice") Double minPrice,
    	        @Param("maxPrice") Double maxPrice,
    	        @Param("sizes") List<String> sizes,
    	        @Param("styles") List<String> styles,
    	        @Param("styleMatch") String styleMatch,
    	        Pageable pageable);

}