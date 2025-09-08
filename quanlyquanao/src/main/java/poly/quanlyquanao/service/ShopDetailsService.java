package poly.quanlyquanao.service;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.repository.ShopDetailsRepository;
import poly.quanlyquanao.service.Impl.IShopDetailsServiceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopDetailsService implements IShopDetailsServiceImpl{
	@Autowired
	ShopDetailsRepository shopDetailsRepository;
	@Override
	public List<ProductDetail> findByProductId(Long productId) {
		// TODO Auto-generated method stub
		return shopDetailsRepository.findByProductId(productId);
	}

	@Override
	public ProductDetail findSelectedProductDetail(Long productId, String size, String imageName) {
		// TODO Auto-generated method stub
		return shopDetailsRepository.findSelectedProductDetail(productId, size, imageName);
	}

}
