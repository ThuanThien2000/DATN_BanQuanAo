package poly.quanlyquanao.service.Impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.dto.ProductInfo;

public interface StatisticServiceImpl {
    BigDecimal getAllRevenue ();
	BigDecimal getRevenueByDate (LocalDateTime startDate, LocalDateTime endDate);
    BigDecimal getRevenueByMonth (int month, int year);
    BigDecimal getRevenueByYear (int year);
    BigDecimal getRevenueToday ();
    List<ProductDTO> getTopSellingProducts(int limit);
    List<ProductDTO> getLowestSellingProducts(int limit);
    List<ProductDTO> getTopSellingProductsByDate(LocalDateTime startDate, LocalDateTime endDate, int limit);
    List<ProductDTO> getLowestSellingProductsByDate(LocalDateTime startDate, LocalDateTime endDate, int limit);
    List<ProductDetailDTO> getLowStockProducts(int threshold);
}
