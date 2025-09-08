package poly.quanlyquanao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.dto.InvoiceDetailDTO;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.mapper.InvoiceDetailMapper;
import poly.quanlyquanao.mapper.InvoiceMapper;
import poly.quanlyquanao.repository.InvoiceDetailRepository;
import poly.quanlyquanao.repository.InvoiceRepository;
import poly.quanlyquanao.repository.PaymentMethodRepository;
import poly.quanlyquanao.repository.ProductDetailRepository;
import poly.quanlyquanao.service.Impl.IInvoiceDetailService;
@Service
public class InvoiceDetailService implements IInvoiceDetailService{
	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
		@Autowired
	PaymentMethodRepository paymentMethodRepository;
		@Autowired
	InvoiceRepository invoiceRepository;
		@Autowired
	ProductDetailRepository productDetailRepository;
	@Override
	public List<InvoiceDetailDTO> findAllInvoiceDetaiByInvoiceID(String invoiceCode) {
		List<InvoiceDetail> details = invoiceDetailRepository.findAllByInvoice_InvoiceCodeAndStatus(invoiceCode, 1);
		return details.stream().map(InvoiceDetailMapper::toDTO).toList();
	}

	@Override
	public InvoiceDetailDTO getInvoiceDetailById(String invoiceCode, Long invoiceDetailId) {
		InvoiceDetail detail = invoiceDetailRepository.findByInvoice_InvoiceCodeAndId(invoiceCode, invoiceDetailId)
			.orElseThrow(() -> new RuntimeException("Không tìm thấy InvoiceDetail với id: "+ invoiceDetailId));
		return InvoiceDetailMapper.toDTO(detail);
	}
	@Override
	public InvoiceDTO getInvoiceByCode(String invoiceCode) {
		return invoiceRepository.findByInvoiceCode(invoiceCode)
			.map(InvoiceMapper::toDTO)
			.orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn với mã: " + invoiceCode));
	}
	@Override
	public Invoice updateInvoiceStatus(String invoiceCode, Integer status) {
		Invoice invoice = invoiceRepository.findByInvoiceCode(invoiceCode)
			.orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn với mã: " + invoiceCode));
		invoice.setStatus(status);
		return invoiceRepository.save(invoice);
	}
<<<<<<< HEAD
<<<<<<< HEAD
}
=======
}
>>>>>>> 24e6ed3616444da2659de8f5147ef376a3049ed9
=======
		@Override
	public Invoice updateInvoiceAddress(String invoiceCode, String deliveryAddress, String phonenumber) {
		Invoice invoice = invoiceRepository.findByInvoiceCode(invoiceCode)
			.orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn với mã: " + invoiceCode));
		invoice.setDeliveryAddress(deliveryAddress);
		invoice.setPhonenumber(phonenumber);
		return invoiceRepository.save(invoice);
	}
	@Override
	public Invoice updatePaymentMethod(String invoiceCode) {
		Invoice invoice = invoiceRepository.findByInvoiceCode(invoiceCode)
			.orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn với mã: " + invoiceCode));
		invoice.setPaymentMethod(paymentMethodRepository.findById(2L)
			.orElseThrow(() -> new RuntimeException("Không tìm thấy phương thức thanh toán với ID: 2")));
		return invoiceRepository.save(invoice);
	}
    @Override
    @Transactional
    public void userCancelInvoice(String invoiceCode) {
        Invoice invoice = invoiceRepository.findByInvoiceCode(invoiceCode)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Invoice với id: " + invoiceCode));

        invoice.setStatus(0);
        invoiceRepository.save(invoice);

        List<InvoiceDetail> invoiceDetailList = invoiceDetailRepository.findAllByInvoice_InvoiceCodeAndStatus(invoiceCode, 1);
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
>>>>>>> origin/phuong_fronten03
