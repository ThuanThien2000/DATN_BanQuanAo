package poly.quanlyquanao.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import poly.quanlyquanao.dto.CartItem;
import poly.quanlyquanao.dto.InvoiceInfo;
import poly.quanlyquanao.model.*;
import poly.quanlyquanao.repository.*;
import poly.quanlyquanao.service.Impl.ICheckoutServiceImpl;

@Service
public class CheckoutService implements ICheckoutServiceImpl {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private UserRepository userRepository;

    // Hàm sinh mã hóa đơn duy nhất
    private String generateUniqueInvoiceCode() {
        String prefix = "INV";
        int maxAttempts = 100;
        for (int i = 0; i < maxAttempts; i++) {
            String code = prefix + String.format("%04d", (int) (Math.random() * 10000));
            if (!invoiceRepository.existsByInvoiceCode(code)) {
                return code;
            }
        }
        throw new RuntimeException("Không thể tạo mã hóa đơn duy nhất sau nhiều lần thử.");
    }

    @Override
    @Transactional
    public Invoice checkoutOrder(InvoiceInfo invoiceInfo, List<CartItem> items) {
        BigDecimal discountAmount = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;

        // Lấy các entity liên quan
        PaymentMethod method = paymentMethodRepository.findById(invoiceInfo.getPaymentMethodId())
                .orElseThrow(() -> new RuntimeException("Phương thức thanh toán không tồn tại"));

        Voucher voucher = null;
        if (invoiceInfo.getVoucherId() != null) {
            voucher = voucherRepository.findById(invoiceInfo.getVoucherId())
                    .orElseThrow(() -> new RuntimeException("Voucher không tồn tại"));
        }

        User staff = null;
        if (invoiceInfo.getAssignedStaffId() != null) {
            staff = userRepository.findById(invoiceInfo.getAssignedStaffId())
                    .orElse(null);
        }

        // Khởi tạo Invoice
        Invoice invoice = new Invoice();
        invoice.setInvoiceCode(generateUniqueInvoiceCode());
        invoice.setFullname(invoiceInfo.getFullname());
        invoice.setPhonenumber(invoiceInfo.getPhonenumber());
        invoice.setEmail(invoiceInfo.getEmail());
        invoice.setDeliveryAddress(invoiceInfo.getDeliveryAddress());
        invoice.setPaymentMethod(method);
        invoice.setShippingFee(invoiceInfo.getShippingFee() != null ? invoiceInfo.getShippingFee() : BigDecimal.ZERO);
        invoice.setVoucher(voucher);
        invoice.setAssignedStaff(staff);
        invoice.setStatus(1);
        invoice.setTotalAmount(BigDecimal.ZERO);
        invoice.setDiscountAmount(BigDecimal.ZERO);
        Invoice savedInvoice = invoiceRepository.save(invoice);

        // Danh sách chi tiết hóa đơn
        List<InvoiceDetail> invoiceDetails = new ArrayList<>();

        for (CartItem item : items) {
            ProductDetail productDetail = productDetailService.findById(item.getProductDetailId());

            // Kiểm tra tồn kho
            if (productDetail.getInventoryQuantity() < item.getQuantity()) {
                throw new RuntimeException("Sản phẩm mã " + productDetail.getProductDetailCode() + " không đủ hàng.");
            }

            BigDecimal price = productDetail.getProduct().getPrice();
            int quantity = item.getQuantity();
            BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(quantity));

            // Tạo hóa đơn chi tiết
            InvoiceDetail detail = new InvoiceDetail();
            detail.setInvoice(savedInvoice);
            detail.setProductDetail(productDetail);
            detail.setPrice(price);
            detail.setQuantity(quantity);
            detail.setTotalPrice(totalPrice);
            detail.setStatus(1);

            invoiceDetails.add(detail);

            // Trừ kho
            productDetail.setInventoryQuantity(productDetail.getInventoryQuantity() - quantity);
            productDetailService.update(productDetail.getId(), productDetail);

            // Cộng vào tổng hóa đơn
            totalAmount = totalAmount.add(totalPrice);
        }

        // Tính giảm giá
        if (voucher != null && voucher.getDiscountPercentage() != null) {
            BigDecimal discountPercent = BigDecimal.valueOf(voucher.getDiscountPercentage())
                    .divide(BigDecimal.valueOf(100));
            discountAmount = totalAmount.multiply(discountPercent);
        }

        // Tổng tiền cuối cùng
        BigDecimal finalAmount = totalAmount.subtract(discountAmount)
                .add(invoice.getShippingFee());

        if (finalAmount.compareTo(BigDecimal.ZERO) < 0) {
            finalAmount = BigDecimal.ZERO;
        }

        // Cập nhật lại invoice
        savedInvoice.setDiscountAmount(discountAmount);
        savedInvoice.setTotalAmount(finalAmount);
        invoiceRepository.save(savedInvoice);

        // Lưu chi tiết hóa đơn
        invoiceDetailRepository.saveAll(invoiceDetails);

        return savedInvoice;
    }
}
