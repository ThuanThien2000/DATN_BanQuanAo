package poly.quanlyquanao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.repository.ProductDetailRepository;


@Service
public class ShopService {
	
	@Autowired
	private ProductDetailRepository productDetailRepository;

	public Page<Product> filterProducts(Double minPrice, Double maxPrice, List<String> sizes, List<String> styles, int page) {
        Pageable pageable = PageRequest.of(page, 5);

        String styleMatch = styles != null && !styles.isEmpty() ? styles.get(0) : "";

        return productDetailRepository.filterProducts(minPrice, maxPrice, sizes, styles, styleMatch, pageable);
    }

}
