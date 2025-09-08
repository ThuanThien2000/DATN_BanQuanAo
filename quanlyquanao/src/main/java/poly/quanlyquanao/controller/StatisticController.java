package poly.quanlyquanao.controller;

<<<<<<< HEAD
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
=======
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.quanlyquanao.service.Impl.StatisticServiceImpl;

@RestController
@RequestMapping("/api/statistics")
@CrossOrigin(origins = "*")
public class StatisticController {
    @Autowired
    StatisticServiceImpl statisticService;
    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() 
    {
        try {
            return ResponseEntity.ok(statisticService.getAllRevenue());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching summary: " + e.getMessage());
        }
    }
    @GetMapping("/revenue-today")
    public ResponseEntity<?> getRevenueToday() {
        try {
            return ResponseEntity.ok(statisticService.getRevenueToday());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching today's revenue: " + e.getMessage());
        }
    }
    @GetMapping("/revenue-month")
    public ResponseEntity<?> getRevenueByMonth(@RequestParam int month, @RequestParam int year) {
        try {
            return ResponseEntity.ok(statisticService.getRevenueByMonth(month, year));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching revenue by month: " + e.getMessage());
        }
    }   
    @GetMapping("/revenue-year")
    public ResponseEntity<?> getRevenueByYear(@RequestParam int year) {
        try {
            return ResponseEntity.ok(statisticService.getRevenueByYear(year));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching revenue by year: " + e.getMessage());
        }
    }
    @GetMapping("/revenue-date")
    public ResponseEntity<?> getRevenueByDate(LocalDateTime startDate, LocalDateTime endDate) {
        try {
            return ResponseEntity.ok(statisticService.getRevenueByDate(startDate, endDate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching revenue by date: " + e.getMessage());
        }
    }
    @GetMapping("/top-selling")
    public ResponseEntity<?> getTopSellingProducts(@RequestParam int limit) {
        try {
            return ResponseEntity.ok(statisticService.getTopSellingProducts(limit));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching top selling products: " + e.getMessage());
        }
    }
    @GetMapping("/lowest-selling")
    public ResponseEntity<?> getLowestSellingProducts(@RequestParam int limit) {
        try {
            return ResponseEntity.ok(statisticService.getLowestSellingProducts(limit));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching lowest selling products: " + e.getMessage());
        }
    }
    @GetMapping("/top-selling-by-date")
    public ResponseEntity<?> getTopSellingProductsByDate(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate, @RequestParam int limit) {
        try {
            return ResponseEntity.ok(statisticService.getTopSellingProductsByDate(startDate, endDate, limit));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching top selling products by date: " + e.getMessage());
        }
    }
    @GetMapping("/lowest-selling-by-date")
    public ResponseEntity<?> getLowestSellingProductsByDate(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate, @RequestParam int limit) {
        try {
            return ResponseEntity.ok(statisticService.getLowestSellingProductsByDate(startDate, endDate, limit));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching lowest selling products by date: " + e.getMessage());
        }
    }
    @GetMapping("/low-stock")
    public ResponseEntity<?> getLowStockProducts(@RequestParam int threshold) {
        try {
            return ResponseEntity.ok(statisticService.getLowStockProducts(threshold));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching low stock products: " + e.getMessage());
        }
    }
    }
>>>>>>> 7deda69b2bcc4f21443b6a1d4d4cd3711c562035
