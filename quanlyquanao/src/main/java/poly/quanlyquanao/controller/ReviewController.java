package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.Review;
import poly.quanlyquanao.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping
	public List<Review> getAllReviews() {
        return reviewService.getAll();
    }
	
	@GetMapping("/product/{productId}")
	public List<Review> getByProduct(@PathVariable Long productId) {
        return reviewService.getByProductId(productId);
    }
	
	@GetMapping("/approved")
    public List<Review> getApprovedReviews() {
        return reviewService.getByStatusOne();
    }
}
