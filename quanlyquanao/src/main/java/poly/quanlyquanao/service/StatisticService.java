package poly.quanlyquanao.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.dto.ImageDTO;
import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.mapper.ProductDetailMapper;
import poly.quanlyquanao.mapper.ProductMapper;
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
    public BigDecimal getAllRevenue() {
        return invoiceRepository.findByStatus(5).stream()
                .map(invoice -> invoice.getTotalAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal getRevenueByDate(LocalDateTime startDate, LocalDateTime endDate) {
        // Implementation here
        return invoiceRepository.findByStatus(5).stream()
                .filter(invoice -> !invoice.getPaymentDate().isBefore(startDate) && !invoice.getPaymentDate().isAfter(endDate))
                .map(invoice -> invoice.getTotalAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal getRevenueByMonth(int month, int year) {
        // Implementation here
        return invoiceRepository.findByStatus(5).stream()
                .filter(invoice -> invoice.getPaymentDate().getMonthValue() == month && invoice.getPaymentDate().getYear() == year)
                .map(invoice -> invoice.getTotalAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal getRevenueByYear(int year) {
        // Implementation here
        return invoiceRepository.findByStatus(5).stream()
                .filter(invoice -> invoice.getPaymentDate().getYear() == year)
                .map(invoice -> invoice.getTotalAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal getRevenueToday() {
        // Implementation here
        return invoiceRepository.findByStatus(5).stream()
                .filter(invoice -> invoice.getPaymentDate().toLocalDate().isEqual(LocalDate.now()))
                .map(invoice -> invoice.getTotalAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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
}
