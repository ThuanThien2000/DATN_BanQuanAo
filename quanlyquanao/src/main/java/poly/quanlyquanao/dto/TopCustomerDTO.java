package poly.quanlyquanao.dto;

public class TopCustomerDTO {
    private Long customerId;
    private String customerName;
    private Double totalSpent;
    private Long totalOrders;

    // Constructor mặc định (bắt buộc cho Hibernate)
    public TopCustomerDTO() {
    }

    // Constructor đầy đủ (bắt buộc phải đúng thứ tự & kiểu với JPQL)
    public TopCustomerDTO(Long customerId, String customerName, Double totalSpent, Long totalOrders) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalSpent = totalSpent;
        this.totalOrders = totalOrders;
    }

    // Getter & Setter
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Long totalOrders) {
        this.totalOrders = totalOrders;
    }
}
