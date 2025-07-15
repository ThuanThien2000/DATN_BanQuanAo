package poly.quanlyquanao.service;
import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.dto.StyleDTO;
import poly.quanlyquanao.mapper.ProductDetailMapper;
import poly.quanlyquanao.mapper.ProductMapper;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.repository.ProductDetailRepository;
import poly.quanlyquanao.repository.ProductRepository;
import poly.quanlyquanao.service.Impl.IShopDetailsServiceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopDetailsService implements IShopDetailsServiceImpl{
	@Autowired
	ProductDetailRepository shopDetailsRepository;
	@Autowired
	ProductRepository productRepository;
	@Override
	public List<ProductDetailDTO> findByProductId(Long productId) {
		// TODO Auto-generated method stub
		return shopDetailsRepository.findByProductId(productId)
				.stream()
				.map(ProductDetailMapper::toDTO)
				.toList();
	}

	@Override
	public ProductDetailDTO findSelectedProductDetail(Long productId, String size, String style) {
		// TODO Auto-generated method stub
		ProductDetail detail = shopDetailsRepository.findFirstByProduct_IdAndSizeAndStyle(productId, size, style);
		return ProductDetailMapper.toDTO(detail);
	}

	@Override
	public List<StyleDTO> findUniqueStylesByProductId(Long productId) {
		List<ProductDetail> details = shopDetailsRepository.findByProductId(productId);
		return details.stream()
				.filter(d -> d.getStyle() != null && !d.getStyle().isEmpty())
				.collect(java.util.stream.Collectors.toMap(
						ProductDetail::getStyle,
						d -> d,
						(d1, d2) -> d1)) // giữ lại ProductDetail đầu tiên cho mỗi style
				.values().stream()
				.map(d -> new StyleDTO(d.getStyle(), d.getImgUrl()))
				.toList();
	}

	@Override
	public List<String> findUniqueSizesByProductId(Long productId) {
		return shopDetailsRepository.findByProductId(productId)
				.stream()
				.map(ProductDetail::getSize)
				.filter(size -> size != null && !size.isEmpty())
				.distinct()
				.toList();
	}
	@Override
	public ProductDTO findProductById(Long productId) {
		return productRepository.findById(productId)
				.map(ProductMapper::toProductDTO)
				.orElse(null);
	}
}
