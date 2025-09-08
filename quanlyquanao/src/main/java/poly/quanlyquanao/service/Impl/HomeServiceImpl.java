package poly.quanlyquanao.service.Impl;

import java.util.List;
import poly.quanlyquanao.dto.ProductInfo;

public interface HomeServiceImpl {
    List<ProductInfo> getFeaturedProducts(Integer limit);

    List<ProductInfo> getNewArrivals(Integer limit);

    List<ProductInfo> getByUserType(String userType); 
}
