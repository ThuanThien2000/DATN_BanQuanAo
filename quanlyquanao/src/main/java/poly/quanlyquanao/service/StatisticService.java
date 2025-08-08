package poly.quanlyquanao.service;

import poly.quanlyquanao.dto.*;
import java.util.List;
import java.time.LocalDate;

public interface StatisticService {

    SummaryDTO getSummary();

    List<RevenueStatDTO> getRevenueByDate(LocalDate startDate, LocalDate endDate);

    List<RevenueStatDTO> getRevenueByMonth(int year);

    List<TopProductDTO> getTopProducts(int limit);

    List<TopCustomerDTO> getTopCustomers(int limit);

    List<LowStockProductDTO> getLowStockProducts(int threshold);

   
    List<OrderStatusDTO> getOrderStatusStatistics();
    List<LowSellingProductDTO> getLowSellingProducts(int days, int maxSold, int minStock);
}
