package poly.quanlyquanao.service;
import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.dto.ProductInfo;
import poly.quanlyquanao.dto.StyleDTO;
import poly.quanlyquanao.mapper.ProductDetailMapper;
import poly.quanlyquanao.mapper.ProductInfoMapper;
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
	public List<ProductDetailDTO> findByProductCode(String productCode) {
		// TODO Auto-generated method stub
		Product product = productRepository.findByProductCode(productCode)
				.orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại với mã: " + productCode));
		return shopDetailsRepository.findByProductId(product.getId())
				.stream()
				.map(ProductDetailMapper::toDTO)
				.toList();
	}

	@Override
	public ProductDetailDTO findSelectedProductDetail(String productCode, String size, String style) {
		// TODO Auto-generated method stub
		Product product = productRepository.findByProductCode(productCode)
				.orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại với mã: " + productCode));
		ProductDetail detail = shopDetailsRepository.findFirstByProduct_IdAndSizeAndStyle(product.getId(), size, style);
		return ProductDetailMapper.toDTO(detail);
	}

	@Override
	public List<StyleDTO> findUniqueStylesByProductId(String productCode) {
				Product product = productRepository.findByProductCode(productCode)
				.orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại với mã: " + productCode));
		List<ProductDetail> details = shopDetailsRepository.findByProductId(product.getId());
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
	public List<String> findUniqueSizesByProductId(String productCode) {
		Product product = productRepository.findByProductCode(productCode)
				.orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại với mã: " + productCode));
		return shopDetailsRepository.findByProductId(product.getId())
				.stream()
				.map(ProductDetail::getSize)
				.filter(size -> size != null && !size.isEmpty())
				.distinct()
				.toList();
	}
	@Override
	public ProductDTO findProductById(String productCode) {
		return productRepository.findByProductCode(productCode)
				.map(ProductMapper::toProductDTO)
				.orElse(null);
	}
	@Override
	public List<ProductInfo> findRelatedProductsByCategoryId(String productCode) {
		Product product = productRepository.findByProductCode(productCode)
				.orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại với mã: " + productCode));
		
		return productRepository.findTop4ByCategory_IdAndStatusOrderByIdDesc(product.getCategory().getId(),1)
				.stream()
				.map(ProductInfoMapper::toProductInfo)
				.toList();
	}

}
