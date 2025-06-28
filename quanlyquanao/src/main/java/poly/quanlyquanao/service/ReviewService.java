package poly.quanlyquanao.service;

import poly.quanlyquanao.model.Review;
import java.util.List;

public interface ReviewService {
	List<Review> getAll();
    List<Review> getByProductId(Long productId);
    List<Review> getByStatusOne(); // chỉ lấy review có status = 1
}
