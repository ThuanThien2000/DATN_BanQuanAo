package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.Review;
import poly.quanlyquanao.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/product/{productId}/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	// GET: Lấy tất cả review của 1 sản phẩm (dành cho admin)
    @GetMapping
    public List<Review> getReviewsByProduct(@PathVariable Long productId) {
        return reviewService.getByProductId(productId);
    }

}
