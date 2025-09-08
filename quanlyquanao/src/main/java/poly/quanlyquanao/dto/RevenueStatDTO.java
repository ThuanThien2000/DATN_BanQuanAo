package poly.quanlyquanao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RevenueStatDTO {
    private String date;      // Cho doanh thu theo ngày
    private Integer month;    // Cho doanh thu theo tháng
    private Double revenue;   // Tổng doanh thu

    public RevenueStatDTO(String date, Double revenue) {
        this.date = date;
        this.revenue = revenue;
    }

    public RevenueStatDTO(Integer month, Double revenue) {
        this.month = month;
        this.revenue = revenue;
    }
}
