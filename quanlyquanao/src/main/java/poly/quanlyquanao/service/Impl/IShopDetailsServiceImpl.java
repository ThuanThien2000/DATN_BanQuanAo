package poly.quanlyquanao.service.Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.repository.ProductDetailRepository;
import poly.quanlyquanao.repository.ProductRepository;
import poly.quanlyquanao.service.ProductDetailService;

import java.util.List;
public interface IShopDetailsServiceImpl {
	List<ProductDetail> findByProductId(Long productId);
	ProductDetail findSelectedProductDetail(Long productId, String size,String style);
}
