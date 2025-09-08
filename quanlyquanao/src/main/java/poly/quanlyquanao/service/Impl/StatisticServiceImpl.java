package poly.quanlyquanao.service.Impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import poly.quanlyquanao.dto.ChartItem;
import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.dto.ProductInfo;

public interface StatisticServiceImpl {
    ChartItem getAllRevenue ();
	ChartItem getRevenueByDate (LocalDateTime startDate, LocalDateTime endDate);
    ChartItem getRevenueByMonth (int month, int year);
    ChartItem getRevenueByYear (int year);
    ChartItem getRevenueToday ();
    List<ChartItem> getAllRevenueChart ();
	List<ChartItem> getRevenueByDateChart (LocalDateTime startDate, LocalDateTime endDate);
    List<ChartItem> getRevenueYearChart (int year);
    List<ChartItem> getRevenueMonthChart ();
    List<ChartItem> getRevenueDayChart ();
    List<ProductDTO> getTopSellingProducts(int limit);
    List<ProductDTO> getLowestSellingProducts(int limit);
    List<ProductDTO> getTopSellingProductsByDate(LocalDateTime startDate, LocalDateTime endDate, int limit);
    List<ProductDTO> getLowestSellingProductsByDate(LocalDateTime startDate, LocalDateTime endDate, int limit);
    List<ProductDetailDTO> getLowStockProducts(int threshold);
}
