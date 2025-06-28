package poly.quanlyquanao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.quanlyquanao.model.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByProductId(Long productId);
	List< Review> findByStatus(Integer status);
}
