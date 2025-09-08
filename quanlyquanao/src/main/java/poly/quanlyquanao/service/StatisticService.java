package poly.quanlyquanao.service;

<<<<<<< HEAD
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
=======
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.dto.ChartItem;
import poly.quanlyquanao.dto.ImageDTO;
import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.mapper.ProductDetailMapper;
import poly.quanlyquanao.mapper.ProductMapper;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.repository.InvoiceRepository;
import poly.quanlyquanao.repository.ProductDetailRepository;
import poly.quanlyquanao.repository.ProductRepository;
import poly.quanlyquanao.service.Impl.StatisticServiceImpl;

@Service
public class StatisticService implements StatisticServiceImpl {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailRepository productDetailRepository;

@Override
public ChartItem getAllRevenue() {
    // Lấy danh sách hóa đơn thành công
    List<Invoice> successInvoices = invoiceRepository.findByStatus(5);

    // Tổng revenue từ hóa đơn thành công
    BigDecimal totalRevenue = successInvoices.stream()
            .map(Invoice::getTotalAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    int countSuccess = successInvoices.size();

    // Lấy danh sách hóa đơn hủy (ví dụ status = 0 là canceled)
    List<Invoice> canceledInvoices = invoiceRepository.findByStatus(0);
    int countCanceled = canceledInvoices.size();

    return new ChartItem("Tổng doanh thu", totalRevenue, countSuccess, countCanceled);
}


@Override
public ChartItem getRevenueByDate(LocalDateTime startDate, LocalDateTime endDate) {
    if (startDate == null || endDate == null) {
        throw new IllegalArgumentException("Start date and end date must not be null");
    }

    // Lấy tất cả hóa đơn trong khoảng ngày
    List<Invoice> invoices = invoiceRepository.findAll().stream()
            .filter(invoice -> {
                LocalDateTime paymentDate = invoice.getPaymentDate();
                return paymentDate != null && !paymentDate.isBefore(startDate) && !paymentDate.isAfter(endDate);
            })
            .toList();

    // Tính tổng revenue từ hóa đơn thành công (status = 5)
    BigDecimal totalRevenue = invoices.stream()
            .filter(inv -> inv.getStatus() == 5)
            .map(Invoice::getTotalAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    // Đếm số hóa đơn thành công
    int countSuccess = (int) invoices.stream()
            .filter(inv -> inv.getStatus() == 5)
            .count();

    // Đếm số hóa đơn hủy (ví dụ status = 0)
    int countCanceled = (int) invoices.stream()
            .filter(inv -> inv.getStatus() == 0)
            .count();

    return new ChartItem("Doanh thu theo ngày", totalRevenue, countSuccess, countCanceled);
}

@Override
public ChartItem getRevenueByMonth(int month, int year) {
    if (month < 1 || month > 12 || year < 0) {
        throw new IllegalArgumentException("Invalid month or year");
    }

    // Lấy tất cả hóa đơn trong tháng/năm đó
    List<Invoice> invoices = invoiceRepository.findAll().stream()
            .filter(invoice -> {
                LocalDateTime paymentDate = invoice.getPaymentDate();
                return paymentDate != null && paymentDate.getMonthValue() == month
                        && paymentDate.getYear() == year;
            })
            .toList();

    // Tổng revenue chỉ tính hóa đơn thành công (status = 5)
    BigDecimal totalRevenue = invoices.stream()
            .filter(inv -> inv.getStatus() == 5)
            .map(Invoice::getTotalAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    // Đếm số hóa đơn thành công
    int countSuccess = (int) invoices.stream()
            .filter(inv -> inv.getStatus() == 5)
            .count();

    // Đếm số hóa đơn hủy (ví dụ status = 0)
    int countCanceled = (int) invoices.stream()
            .filter(inv -> inv.getStatus() == 0)
            .count();

    // Label ví dụ: "Doanh thu 08/2025"
    String label = String.format("Doanh thu %02d/%d", month, year);

    return new ChartItem(label, totalRevenue, countSuccess, countCanceled);
}


@Override
public ChartItem getRevenueByYear(int year) {
    if (year < 0) {
        throw new IllegalArgumentException("Year must be a positive integer");
    }

    // Lấy tất cả hóa đơn trong năm đó
    List<Invoice> invoices = invoiceRepository.findAll().stream()
            .filter(invoice -> {
                LocalDateTime paymentDate = invoice.getPaymentDate();
                return paymentDate != null && paymentDate.getYear() == year;
            })
            .toList();

    // Tính tổng doanh thu chỉ với hóa đơn thành công (status = 5)
    BigDecimal totalRevenue = invoices.stream()
            .filter(inv -> inv.getStatus() == 5)
            .map(Invoice::getTotalAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    // Đếm số hóa đơn thành công
    int countSuccess = (int) invoices.stream()
            .filter(inv -> inv.getStatus() == 5)
            .count();

    // Đếm số hóa đơn hủy (status = 0)
    int countCanceled = (int) invoices.stream()
            .filter(inv -> inv.getStatus() == 0)
            .count();

    // Label ví dụ: "Doanh thu 2025"
    String label = String.format("Doanh thu %d", year);

    return new ChartItem(label, totalRevenue, countSuccess, countCanceled);
}


@Override
public ChartItem getRevenueToday() {
    LocalDate today = LocalDate.now();

    // Lấy tất cả hóa đơn trong ngày hôm nay
    List<Invoice> invoices = invoiceRepository.findAll().stream()
            .filter(invoice -> {
                LocalDateTime paymentDate = invoice.getPaymentDate();
                return paymentDate != null && paymentDate.toLocalDate().isEqual(today);
            })
            .toList();

    // Tính tổng doanh thu (chỉ tính status = 5)
    BigDecimal totalRevenue = invoices.stream()
            .filter(inv -> inv.getStatus() == 5)
            .map(Invoice::getTotalAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    // Đếm số hóa đơn thành công
    int countSuccess = (int) invoices.stream()
            .filter(inv -> inv.getStatus() == 5)
            .count();

    // Đếm số hóa đơn hủy
    int countCanceled = (int) invoices.stream()
            .filter(inv -> inv.getStatus() == 0)
            .count();

    return new ChartItem("Doanh thu hôm nay", totalRevenue, countSuccess, countCanceled);
}


@Override
public List<ChartItem> getAllRevenueChart() {
    // Lấy tất cả hóa đơn và group theo năm
    List<Invoice> AllInvoices = invoiceRepository.findAll();

    Map<Integer, List<Invoice>> groupedByYear = AllInvoices.stream()
            .filter(invoice -> invoice.getPaymentDate() != null) // lọc trước
            .collect(Collectors.groupingBy(
                    invoice -> invoice.getPaymentDate().toLocalDate().getYear()
            ));

    // Chuyển thành List<ChartItem>
    return groupedByYear.entrySet().stream()
            .map(entry -> {
                Integer year = entry.getKey();
                List<Invoice> invoices = entry.getValue();

                // Tổng revenue (chỉ tính hóa đơn thành công)
                BigDecimal totalRevenue = invoices.stream()
                        .filter(inv -> inv.getStatus() == 5)
                        .map(Invoice::getTotalAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                // Đếm số hóa đơn thành công
                int countSuccess = (int) invoices.stream()
                        .filter(inv -> inv.getStatus() == 5)
                        .count();

                // Đếm số hóa đơn hủy
                int countCanceled = (int) invoices.stream()
                        .filter(inv -> inv.getStatus() == 0)
                        .count();

                return new ChartItem(String.valueOf(year), totalRevenue, countSuccess, countCanceled);
            })
            .sorted(Comparator.comparing(ChartItem::getLabel)) // sắp xếp theo năm
            .collect(Collectors.toList());
}

@Override
public List<ChartItem> getRevenueByDateChart(LocalDateTime startDate, LocalDateTime endDate) {
    if (startDate == null || endDate == null) {
        throw new IllegalArgumentException("Start date and end date must not be null");
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Lấy tất cả hóa đơn trong khoảng startDate - endDate
    List<Invoice> filteredInvoices = invoiceRepository.findAll().stream()
            .filter(invoice -> {
                LocalDateTime paymentDate = invoice.getPaymentDate();
                return paymentDate != null &&
                       !paymentDate.isBefore(startDate) &&
                       !paymentDate.isAfter(endDate);
            })
            .toList();

    // Group theo ngày (dd/MM/yyyy)
    Map<String, List<Invoice>> groupedByDate = filteredInvoices.stream()
            .collect(Collectors.groupingBy(
                    invoice -> invoice.getPaymentDate().toLocalDate().format(formatter)
            ));

    // Map sang List<ChartItem>
    return groupedByDate.entrySet().stream()
            .map(entry -> {
                String date = entry.getKey();
                List<Invoice> invoices = entry.getValue();

                // Tổng revenue chỉ tính thành công
                BigDecimal totalRevenue = invoices.stream()
                        .filter(inv -> inv.getStatus() == 5)
                        .map(Invoice::getTotalAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                // Đếm hóa đơn thành công
                int countSuccess = (int) invoices.stream()
                        .filter(inv -> inv.getStatus() == 5)
                        .count();

                // Đếm hóa đơn hủy
                int countCanceled = (int) invoices.stream()
                        .filter(inv -> inv.getStatus() == 0)
                        .count();

                return new ChartItem(date, totalRevenue, countSuccess, countCanceled);
            })
            // sắp xếp theo ngày tăng dần
            .sorted(Comparator.comparing(entry ->
                    LocalDate.parse(entry.getLabel(), formatter)
            ))
            .collect(Collectors.toList());
}

@Override
public List<ChartItem> getRevenueYearChart(int year) {
    if (year < 0) {
        throw new IllegalArgumentException("Year must be a positive integer");
    }

    // Lấy tất cả hóa đơn của năm đó
    List<Invoice> invoicesOfYear = invoiceRepository.findAll().stream()
            .filter(invoice -> invoice.getPaymentDate() != null &&
                               invoice.getPaymentDate().getYear() == year)
            .toList();

    // Group theo "MM/YYYY"
    Map<String, List<Invoice>> groupedByMonth = invoicesOfYear.stream()
            .collect(Collectors.groupingBy(
                    invoice -> {
                        LocalDate date = invoice.getPaymentDate().toLocalDate();
                        return String.format("%02d/%04d", date.getMonthValue(), date.getYear()); // MM/YYYY
                    }
            ));

    return groupedByMonth.entrySet().stream()
            .map(entry -> {
                String label = entry.getKey(); // MM/YYYY
                List<Invoice> invoices = entry.getValue();

                // Tổng revenue chỉ tính success
                BigDecimal totalRevenue = invoices.stream()
                        .filter(inv -> inv.getStatus() == 5)
                        .map(Invoice::getTotalAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                // Đếm success
                int countSuccess = (int) invoices.stream()
                        .filter(inv -> inv.getStatus() == 5)
                        .count();

                // Đếm canceled
                int countCanceled = (int) invoices.stream()
                        .filter(inv -> inv.getStatus() == 0)
                        .count();

                return new ChartItem(label, totalRevenue, countSuccess, countCanceled);
            })
            // sắp xếp theo YYYYMM
            .sorted(Comparator.comparing(entry -> {
                String key = entry.getLabel(); // "MM/YYYY"
                int month = Integer.parseInt(key.substring(0, 2));
                int y = Integer.parseInt(key.substring(3));
                return y * 100 + month;
            }))
            .collect(Collectors.toList());
}

@Override
public List<ChartItem> getRevenueMonthChart() {
    LocalDate today = LocalDate.now();

    // Xác định ngày đầu tháng và cuối tháng
    LocalDate startOfMonth = today.withDayOfMonth(1);
    LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

    // Lấy tất cả invoices trong tháng
    List<Invoice> invoicesOfMonth = invoiceRepository.findAll().stream()
            .filter(invoice -> {
                LocalDateTime paymentDate = invoice.getPaymentDate();
                return paymentDate != null && !paymentDate.toLocalDate().isBefore(startOfMonth) && !paymentDate.toLocalDate().isAfter(endOfMonth);
            })
            .toList();

    // Group theo LocalDate
    Map<LocalDate, List<Invoice>> groupedByDay = invoicesOfMonth.stream()
            .collect(Collectors.groupingBy(invoice -> invoice.getPaymentDate().toLocalDate()));

    return groupedByDay.entrySet().stream()
            .sorted(Map.Entry.comparingByKey()) // sort trực tiếp theo LocalDate
            .map(entry -> {
                LocalDate day = entry.getKey();
                List<Invoice> invoices = entry.getValue();

                // Revenue = chỉ cộng hóa đơn thành công
                BigDecimal totalRevenue = invoices.stream()
                        .filter(inv -> inv.getStatus() == 5)
                        .map(Invoice::getTotalAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                int countSuccess = (int) invoices.stream().filter(inv -> inv.getStatus() == 5).count();
                int countCanceled = (int) invoices.stream().filter(inv -> inv.getStatus() == 0).count();

                // format label = "dd/MM"
                return new ChartItem(day.format(formatter), totalRevenue, countSuccess, countCanceled);
            })
            .collect(Collectors.toList());
}

@Override
public List<ChartItem> getRevenueDayChart() {
    // Lấy ngày cần thống kê (bỏ phần giờ, phút, giây)

    LocalDate today = LocalDate.now();

    // Định dạng giờ (VD: 08, 09, 10...)
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:00");

    // Lấy tất cả hóa đơn trong ngày đó
    List<Invoice> invoicesOfDay = invoiceRepository.findAll().stream()
            .filter(invoice -> {
                LocalDateTime paymentDate = invoice.getPaymentDate();
                return paymentDate != null && paymentDate.toLocalDate().isEqual(today);
            })
            .toList();

    // Group theo giờ (HH:00)
    Map<String, List<Invoice>> groupedByHour = invoicesOfDay.stream()
            .collect(Collectors.groupingBy(
                    invoice -> invoice.getPaymentDate().format(formatter)
            ));

    // Build ChartItem list
    return groupedByHour.entrySet().stream()
            .map(entry -> {
                String hour = entry.getKey();
                List<Invoice> invoices = entry.getValue();

                // Revenue = chỉ cộng hóa đơn thành công
                BigDecimal totalRevenue = invoices.stream()
                        .filter(inv -> inv.getStatus() == 5)
                        .map(Invoice::getTotalAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                int countSuccess = (int) invoices.stream()
                        .filter(inv -> inv.getStatus() == 5)
                        .count();

                int countCanceled = (int) invoices.stream()
                        .filter(inv -> inv.getStatus() == 0)
                        .count();

                return new ChartItem(hour, totalRevenue, countSuccess, countCanceled);
            })
            // Sắp xếp theo giờ trong ngày (0h → 23h)
            .sorted(Comparator.comparing(entry ->
                    LocalTime.parse(entry.getLabel(), formatter)
            ))
            .collect(Collectors.toList());
}



    @Override
    public List<ProductDTO> getTopSellingProducts(int limit) {
        // Implementation here
        return productRepository.findAllActive().stream()
                .map(ProductMapper::toProductDTO)
                .sorted((p1, p2) -> Integer.compare(p2.getTotalSold(), p1.getTotalSold()))
                .limit(limit)
                .collect(Collectors.toList());
    }
    @Override
    public List<ProductDTO> getLowestSellingProducts(int limit) {
        // Implementation here
        return productRepository.findAllActive().stream()
                .map(ProductMapper::toProductDTO)
                .sorted((p1, p2) -> Integer.compare(p1.getTotalSold(), p2.getTotalSold()))
                .limit(limit)
                .collect(Collectors.toList());
    }

// Mapper
     public static ProductDTO toProductDTObyDate(Product product,LocalDateTime startDate, LocalDateTime endDate ) {
        Set<ImageDTO> imageDTOs = null;
        if (product.getImages() != null && !product.getImages().isEmpty()) {
            imageDTOs = product.getImages().stream()
                .filter(img -> img.getStatus() == 1)
                .map(img -> new ImageDTO(img.getId(), img.getImageUrl(), img.getStatus()))
                .collect(Collectors.toSet());
        }

        List<ProductDetail> productDetails = product.getProductDetails() != null ? product.getProductDetails().stream()
            .filter(pd -> pd.getStatus() == 1)
            .collect(Collectors.toList()) : null;
        List<InvoiceDetail> invoiceDetails = productDetails != null ? productDetails.stream()
            .flatMap(pd -> pd.getInvoiceDetails().stream())
            .filter(id -> id.getStatus() == 1 && !id.getInvoice().getPaymentDate().isBefore(startDate) && !id.getInvoice().getPaymentDate().isAfter(endDate))
            .collect(Collectors.toList()) : null;
        Integer totalSold = invoiceDetails != null ? invoiceDetails.stream()
            .mapToInt(InvoiceDetail::getQuantity)
            .sum() : 0;

        return new ProductDTO(
            product.getId(),
            product.getProductCode(),
            product.getProductName(),
            product.getBrand(),
            product.getCategory() != null ? product.getCategory().getId() : null,
            product.getCategory() != null ? product.getCategory().getCategoryName() : null,
            product.getUserType(),
            product.getMaterial(),
            product.getDescription(),
            product.getPrice(),
            totalSold,
            product.getIsFeatured(),
            imageDTOs,
            product.getStatus()
        );
    }

    @Override
    public List<ProductDTO> getTopSellingProductsByDate(LocalDateTime startDate, LocalDateTime endDate, int limit) {
        // Implementation here
        return productRepository.findAllActive().stream()
                .map(product -> StatisticService.toProductDTObyDate(product, startDate, endDate))
                .sorted((p1, p2) -> Integer.compare(p2.getTotalSold(), p1.getTotalSold()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getLowestSellingProductsByDate(LocalDateTime startDate, LocalDateTime endDate, int limit) {
        // Implementation here
        return productRepository.findAllActive().stream()
                .map(product -> StatisticService.toProductDTObyDate(product, startDate, endDate))
                .sorted((p1, p2) -> Integer.compare(p1.getTotalSold(), p2.getTotalSold()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDetailDTO> getLowStockProducts(int threshold) {
        // Implementation here
        return productDetailRepository.findAll().stream()
            .filter(pd -> pd.getStatus() == 1) // chỉ lấy sản phẩm active
            .filter(pd -> pd.getInventoryQuantity() < threshold) // tồn kho thấp hơn threshold
            .map(ProductDetailMapper::toDTO) // map sang DTO
            .collect(Collectors.toList());
    }
>>>>>>> 7deda69b2bcc4f21443b6a1d4d4cd3711c562035
}
