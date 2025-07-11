package poly.quanlyquanao.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.model.Voucher;
import poly.quanlyquanao.repository.InvoiceDetailRepository;
import poly.quanlyquanao.repository.InvoiceRepository;
import poly.quanlyquanao.service.Impl.ICheckoutServiceImpl;

@Service
public class CheckoutService implements ICheckoutServiceImpl {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceDetailRepository invoiceDetailRepository;

    @Override
    public Invoice checkoutOrder(Invoice newInvoice, List<InvoiceDetail> invoiceDetailList) {
        BigDecimal discountAmount = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;

        // 1. Lưu hóa đơn (để lấy ID)
        Invoice savedInvoice = invoiceRepository.save(newInvoice);

        // 2. Gán invoice vào từng chi tiết và tính tổng tiền
        for (InvoiceDetail detail : invoiceDetailList) {
            detail.setInvoice(savedInvoice);
            totalAmount = totalAmount.add(detail.getTotalPrice());
        }

        // 3. Tính giảm giá nếu có voucher
        Voucher voucher = savedInvoice.getVoucher();
        if (voucher != null && voucher.getDiscountPercentage() != null) {
            BigDecimal discountPercent = BigDecimal.valueOf(voucher.getDiscountPercentage())
                                                   .divide(BigDecimal.valueOf(100));
            discountAmount = totalAmount.multiply(discountPercent);
        }

        // 4. Tính tổng cuối cùng: total - discount + shipping
        BigDecimal finalAmount = totalAmount.subtract(discountAmount)
                                            .add(savedInvoice.getShippingFee());

        // 5. Cập nhật lại invoice
        savedInvoice.setDiscountAmount(discountAmount);
        savedInvoice.setTotalAmount(finalAmount);
        invoiceRepository.save(savedInvoice); // cập nhật lại với total & discount

        // 6. Lưu chi tiết hóa đơn
        invoiceDetailRepository.saveAll(invoiceDetailList);

        return savedInvoice;
    }
}
