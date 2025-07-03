package poly.quanlyquanao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import poly.quanlyquanao.model.ProductDetail;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    
    List<ProductDetail> findByProductId(Long productId);

    @Query("SELECT pd FROM ProductDetail pd WHERE pd.product.id = :productId AND pd.status = 1")
    List<ProductDetail> findActiveByProductId(Long productId);

    @Query("SELECT p FROM ProductDetail p WHERE " +
           "(:categoryId IS NULL OR p.product.category.id = :categoryId) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
           "(:size IS NULL OR p.size = :size) AND " +
           "(:style IS NULL OR p.style LIKE %:style%)")
    Page<ProductDetail> filterWithPaging(
        @Param("categoryId") Long categoryId,
        @Param("minPrice") Double minPrice,
        @Param("maxPrice") Double maxPrice,
        @Param("size") String size,
        @Param("style") String style,
        Pageable pageable
    );

}