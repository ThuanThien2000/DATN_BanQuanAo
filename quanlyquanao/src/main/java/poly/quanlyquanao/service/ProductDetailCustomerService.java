package poly.quanlyquanao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.repository.ProductDetailRepository;

@Service
public class ProductDetailCustomerService {
	
	@Autowired
    private ProductDetailRepository productDetailRepository;

	public Page<ProductDetail> filter(Long categoryId, Double minPrice, Double maxPrice, String size, String style, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return productDetailRepository.filterWithPaging(categoryId, minPrice, maxPrice, size, style, pageable);
    }
}
