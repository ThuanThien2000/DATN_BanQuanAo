package poly.quanlyquanao.service.Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.dto.ProductInfo;
import poly.quanlyquanao.dto.StyleDTO;
import poly.quanlyquanao.model.Image;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.repository.ProductDetailRepository;
import poly.quanlyquanao.repository.ProductRepository;
import poly.quanlyquanao.service.ProductDetailService;

import java.util.List;
public interface IShopDetailsServiceImpl {
	ProductDTO findProductById(Long productId);
	List<ProductDetailDTO> findByProductId(Long productId);
	ProductDetailDTO findSelectedProductDetail(Long productId, String size,String style);
	List<StyleDTO> findUniqueStylesByProductId(Long productId);
	List<String> findUniqueSizesByProductId(Long productId);
	List<ProductInfo> findRelatedProductsByCategoryId(Long categoryId);
}
