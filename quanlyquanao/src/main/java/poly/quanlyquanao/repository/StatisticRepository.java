package poly.quanlyquanao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import poly.quanlyquanao.dto.*;
import poly.quanlyquanao.model.Invoice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<Invoice, Long> {

    // 1. Tổng doanh thu, số đơn hàng, khách hàng, sản phẩm đã bán
    @Query("""
        SELECT new poly.quanlyquanao.dto.SummaryDTO(
            SUM(i.totalAmount),
            COUNT(i.id),
            COUNT(DISTINCT i.user.id),
            SUM(id.quantity)
        )
        FROM Invoice i
        JOIN i.invoiceDetails id
    """)
    SummaryDTO getSummary();

    // 2. Doanh thu hôm nay
    @Query("""
        SELECT SUM(i.totalAmount)
        FROM Invoice i
        WHERE FUNCTION('DATE', i.creationDate) = CURRENT_DATE
    """)
    Double getRevenueToday();

    // 3. Doanh thu theo ngày (trong khoảng từ - đến)
    @Query("""
         SELECT new poly.quanlyquanao.dto.RevenueStatDTO(
        FUNCTION('DATE_FORMAT', i.creationDate, '%Y-%m-%d'),
        SUM(i.totalAmount)
    )
    FROM Invoice i
    WHERE i.creationDate BETWEEN :from AND :to
    GROUP BY FUNCTION('DATE_FORMAT', i.creationDate, '%Y-%m-%d')
    ORDER BY FUNCTION('DATE_FORMAT', i.creationDate, '%Y-%m-%d')
    """)
    List<RevenueStatDTO> getRevenueByDay(@Param("from") Date from, @Param("to") Date to);

    // 4. Doanh thu theo tháng trong năm
    @Query("""
         SELECT new poly.quanlyquanao.dto.RevenueStatDTO(
        MONTH(i.creationDate),
        SUM(i.totalAmount)
    )
    FROM Invoice i
    WHERE YEAR(i.creationDate) = :year
    GROUP BY MONTH(i.creationDate)
    ORDER BY MONTH(i.creationDate)
    """)
    List<RevenueStatDTO> getRevenueByMonth(@Param("year") int year);

    // 5. Top sản phẩm bán chạy
    @Query("""
        SELECT new poly.quanlyquanao.dto.TopProductDTO(
    p.id,
    p.productName,
    SUM(id.quantity)
)
FROM InvoiceDetail id
JOIN id.productDetail pd
JOIN pd.product p
GROUP BY p.id, p.productName
ORDER BY SUM(id.quantity) DESC
    """)
    List<TopProductDTO> getTopProducts(Pageable pageable);

    // 6. Top khách hàng mua nhiều
    @Query("""
        SELECT new poly.quanlyquanao.dto.TopCustomerDTO(
        u.id,
        u.fullname,
        SUM(i.totalAmount),
        COUNT(i.id)
    )
    FROM Invoice i
    JOIN i.user u
    GROUP BY u.id, u.fullname
    ORDER BY SUM(i.totalAmount) DESC
    """)
    List<TopCustomerDTO> getTopCustomers(Pageable pageable);

    // 7. Thống kê đơn hàng theo trạng thái
    @Query("SELECT new poly.quanlyquanao.dto.OrderStatusDTO(i.status, COUNT(i)) FROM Invoice i GROUP BY i.status")
    List<OrderStatusDTO> thongKeSoLuongHoaDonTheoTrangThai();

    // 8. Sản phẩm tồn kho thấp
    @Query("""
        SELECT new poly.quanlyquanao.dto.LowStockProductDTO(
        pd.product.id,
        pd.product.productName,
        pd.inventoryQuantity
    )
    FROM ProductDetail pd
    WHERE pd.inventoryQuantity < :threshold
    """)
    List<LowStockProductDTO> getLowStockProducts(@Param("threshold") int threshold);

    // 9. Sản phẩm bán ế (sửa hoàn chỉnh)
    @Query("""
    	    SELECT new poly.quanlyquanao.dto.LowStockProductDTO(
        pd.product.id,
        pd.product.productName,
        pd.inventoryQuantity
    )
    FROM ProductDetail pd
    LEFT JOIN pd.invoiceDetails id
    GROUP BY pd.product.id, pd.product.productName, pd.inventoryQuantity
    HAVING SUM(COALESCE(id.quantity, 0)) < :soldThreshold
    	""")
    List<LowStockProductDTO> findLowSellingProducts(@Param("soldThreshold") Long soldThreshold);
}
