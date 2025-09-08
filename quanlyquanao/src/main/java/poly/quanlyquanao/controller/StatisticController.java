package poly.quanlyquanao.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.dto.*;
import poly.quanlyquanao.service.StatisticService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/statistics")

public class StatisticController {
	@Autowired
    private StatisticService statisticService;
    
    
	public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }
    // ✅ 1. Tổng doanh thu, đơn hàng, khách hàng, sản phẩm đã bán
    // GET http://localhost:8080/api/statistics/summary
    @GetMapping("/summary")
    public ResponseEntity<SummaryDTO> getSummary() {
        return ResponseEntity.ok(statisticService.getSummary());
    }

    // ✅ 2. Doanh thu hôm nay
    // GET http://localhost:8080/api/statistics/revenue-today
    @GetMapping("/revenue-today")
    public ResponseEntity<Double> getRevenueToday() {
        Double revenueToday = statisticService.getRevenueByDate(LocalDate.now(), LocalDate.now())
                .stream()
                .mapToDouble(RevenueStatDTO::getTotalRevenue)
                .sum();
        return ResponseEntity.ok(revenueToday);
    }

    // ✅ 3. Doanh thu theo ngày (từ - đến)
    // Test link: GET http://localhost:8080/api/statistics/revenue-by-day?fromDate=2025-08-01&toDate=2025-08-08
    @GetMapping("/revenue-by-day")
    public ResponseEntity<List<RevenueStatDTO>> getRevenueByDay(
            @RequestParam String fromDate,
            @RequestParam String toDate
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate from = LocalDate.parse(fromDate, formatter);
        LocalDate to = LocalDate.parse(toDate, formatter);

        return ResponseEntity.ok(statisticService.getRevenueByDate(from, to));
    }

    // ✅ 4. Doanh thu theo tháng trong năm
    // GET http://localhost:8080/api/statistics/revenue-by-month?year=2025
    @GetMapping("/revenue-by-month")
    public ResponseEntity<List<RevenueStatDTO>> getRevenueByMonth(@RequestParam int year) {
        return ResponseEntity.ok(statisticService.getRevenueByMonth(year));
    }

    // ✅ 5. Top sản phẩm bán chạy
    // GET http://localhost:8080/api/statistics/top-products?limit=5
    @GetMapping("/top-products")
    public ResponseEntity<List<TopProductDTO>> getTopProducts(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(statisticService.getTopProducts(limit));
    }

    // ✅ 6. Top khách hàng mua nhiều
    // GET http://localhost:8080/api/statistics/top-customers?limit=5
    @GetMapping("/top-customers")
    public ResponseEntity<List<TopCustomerDTO>> getTopCustomers(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(statisticService.getTopCustomers(limit));
    }

    // ✅ 7. Thống kê đơn hàng theo trạng thái
    // GET http://localhost:8080/api/statistics/order-status
    @GetMapping("/order-status")
    public ResponseEntity<List<OrderStatusDTO>> getOrderStatusStatistics() {
        return ResponseEntity.ok(statisticService.getOrderStatusStatistics());
    }

    // ✅ 8. Sản phẩm tồn kho thấp
    // GET http://localhost:8080/api/statistics/low-stock?threshold=10
    @GetMapping("/low-stock")
    public ResponseEntity<List<LowStockProductDTO>> getLowStockProducts(@RequestParam(defaultValue = "10") int threshold) {
        return ResponseEntity.ok(statisticService.getLowStockProducts(threshold));
    }

    // ✅ 9. Sản phẩm bán ế
    // GET http://localhost:8080/api/statistics/low-selling-products?days=30&maxSold=5&minStock=10
    @GetMapping("/low-selling-products")
    public ResponseEntity<List<LowSellingProductDTO>> getLowSellingProducts(
            @RequestParam(defaultValue = "30") int days,
            @RequestParam(defaultValue = "5") int maxSold,
            @RequestParam(defaultValue = "10") int minStock
    ) {
        return ResponseEntity.ok(statisticService.getLowSellingProducts(days, maxSold, minStock));
    }
}