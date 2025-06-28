package poly.quanlyquanao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.Review;
import poly.quanlyquanao.repository.ReviewRepository;
import poly.quanlyquanao.service.ReviewService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public List<Review>getAll(){
		return reviewRepository.findAll();
	}
	
	@Override 
	public List<Review> getByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }
	
	@Override
    public List<Review> getByStatusOne() {
        return reviewRepository.findByStatus(1);
    }
}
