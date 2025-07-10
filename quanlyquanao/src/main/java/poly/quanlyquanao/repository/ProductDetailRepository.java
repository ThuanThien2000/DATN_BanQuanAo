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
    
    ProductDetail findFirstByProduct_IdAndSizeAndStyle(Long productId, String size, String style);

}