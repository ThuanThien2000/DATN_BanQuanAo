package poly.quanlyquanao.controller;

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
    @GetMapping("/chart-all")
    public ResponseEntity<?> getAllRevenueChart() {
        try {
            return ResponseEntity.ok(statisticService.getAllRevenueChart());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching all revenue chart: " + e.getMessage());
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
    @GetMapping("/chart-month")
    public ResponseEntity<?> getRevenueMonthChart() {
        try {
            return ResponseEntity.ok(statisticService.getRevenueMonthChart());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching month's revenue chart: " + e.getMessage());
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
    @GetMapping("/chart-year")
    public ResponseEntity<?> getRevenueByYearChart(@RequestParam int year) {
        try {
            return ResponseEntity.ok(statisticService.getRevenueYearChart(year));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching year's revenue chart: " + e.getMessage());
        }
    }
    @GetMapping("/revenue-date")
    public ResponseEntity<?> getRevenueByDate(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        try {
            return ResponseEntity.ok(statisticService.getRevenueByDate(startDate, endDate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching revenue by date: " + e.getMessage());
        }
    }
    @GetMapping("/chart-hour")
    public ResponseEntity<?> getRevenueByDay() {
        try {
            return ResponseEntity.ok(statisticService.getRevenueDayChart());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching revenue by day: " + e.getMessage());
        }
    }
        @GetMapping("/chart-date")
    public ResponseEntity<?> getRevenueByDateChart(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        try {
            return ResponseEntity.ok(statisticService.getRevenueByDateChart(startDate, endDate));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching date's revenue chart: " + e.getMessage());
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
