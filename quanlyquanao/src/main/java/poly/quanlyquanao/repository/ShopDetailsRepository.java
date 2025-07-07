package poly.quanlyquanao.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import poly.quanlyquanao.model.ProductDetail;

@Repository
public interface ShopDetailsRepository extends JpaRepository<ProductDetail, Long>{
	List<ProductDetail> findByProductId(Long productId);
	@Query(value = "SELECT * FROM product_detail WHERE product_id = :productId AND size = :size AND img_url LIKE CONCAT('%', :imageName, '%') LIMIT 1", nativeQuery = true)
	ProductDetail findSelectedProductDetail(
	    @Param("productId") Long productId,
	    @Param("size") String size,
	    @Param("imageName") String imageName
	);
}
