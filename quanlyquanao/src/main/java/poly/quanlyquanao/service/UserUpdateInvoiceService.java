package poly.quanlyquanao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.repository.InvoiceDetailRepository;
import poly.quanlyquanao.repository.InvoiceRepository;
import poly.quanlyquanao.repository.ProductDetailRepository;
import poly.quanlyquanao.service.Impl.IUserUpdateInvoiceImpl;

@Service
public class UserUpdateInvoiceService implements IUserUpdateInvoiceImpl {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Invoice với id: " + id));
    }

    @Override
    @Transactional
    public void userCancelInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Invoice với id: " + id));

        invoice.setStatus(0);
        invoiceRepository.save(invoice);

        List<InvoiceDetail> invoiceDetailList = invoiceDetailRepository.findAllByInvoice_IdAndStatus(id, 1);
        for (InvoiceDetail invoiceDetail : invoiceDetailList) {
            ProductDetail productDetail = invoiceDetail.getProductDetail();

            int updatedQty = productDetail.getInventoryQuantity() + invoiceDetail.getQuantity();
            productDetail.setInventoryQuantity(updatedQty);
            productDetailRepository.save(productDetail);

            invoiceDetail.setStatus(0);
            invoiceDetailRepository.save(invoiceDetail); // ⚠️ quan trọng
        }
    }
}
