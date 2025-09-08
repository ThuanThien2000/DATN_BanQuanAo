package poly.quanlyquanao.service.Impl;

<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.dto.*;
import poly.quanlyquanao.repository.StatisticRepository;
import poly.quanlyquanao.service.StatisticService;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;

    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public SummaryDTO getSummary() {
        return statisticRepository.getSummary();
    }

    @Override
    public List<RevenueStatDTO> getRevenueByDate(LocalDate startDate, LocalDate endDate) {
        return statisticRepository.getRevenueByDay(
            java.sql.Date.valueOf(startDate),
            java.sql.Date.valueOf(endDate)
        );
    }

    @Override
    public List<RevenueStatDTO> getRevenueByMonth(int year) {
        return statisticRepository.getRevenueByMonth(year);
    }

    @Override
    public List<TopProductDTO> getTopProducts(int limit) {
        return statisticRepository.getTopProducts(PageRequest.of(0, limit));
    }

    @Override
    public List<TopCustomerDTO> getTopCustomers(int limit) {
        return statisticRepository.getTopCustomers(PageRequest.of(0, limit));
    }

    @Override
    public List<LowStockProductDTO> getLowStockProducts(int threshold) {
        return statisticRepository.getLowStockProducts(threshold);
    }

    @Override
    public List<OrderStatusDTO> getOrderStatusStatistics() {
        return statisticRepository.getOrderStatusStatistics();
    }

    @Override
    public List<LowSellingProductDTO> getLowSellingProducts(int days, int maxSold, int minStock) {
        LocalDate fromDate = LocalDate.now().minusDays(days);
        return statisticRepository.getLowSellingProducts(java.sql.Date.valueOf(fromDate), maxSold, minStock);
    }
=======
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
>>>>>>> 7deda69b2bcc4f21443b6a1d4d4cd3711c562035
}
