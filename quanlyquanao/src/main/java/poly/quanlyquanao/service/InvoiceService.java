package poly.quanlyquanao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.repository.InvoiceRepository;
import poly.quanlyquanao.service.Impl.IInvoiceServiceImpl;
import poly.quanlyquanao.repository.InvoiceRepository;
@Service
public class InvoiceService implements IInvoiceServiceImpl{
	private InvoiceDTO toDTO(Invoice invoice) {
		if (invoice == null) return null;
		String imageFirstProduct = null;
		if (invoice.getInvoiceDetails() != null && !invoice.getInvoiceDetails().isEmpty()) {
			InvoiceDetail firstDetail = invoice.getInvoiceDetails().iterator().next();
			if (firstDetail != null && firstDetail.getProductDetail() != null) {
				imageFirstProduct = firstDetail.getProductDetail().getImgUrl();
			}
		}
		return new InvoiceDTO(
			invoice.getId(),
			invoice.getInvoiceCode(),
			invoice.getUser() != null ? invoice.getUser().getId() : null,
			invoice.getFullname(),
			invoice.getPhonenumber(),
			invoice.getEmail(),
			invoice.getDeliveryAddress(),
			invoice.getPaymentMethod() != null ? invoice.getPaymentMethod().getId() : null,
			invoice.getCreationDate(),
			invoice.getShippingFee(),
			invoice.getVoucher() != null ? invoice.getVoucher().getId() : null,
			invoice.getDiscountAmount(),
			invoice.getTotalAmount(),
			invoice.getAssignedStaff() != null ? invoice.getAssignedStaff().getId() : null,
			imageFirstProduct,
			invoice.getStatus()
		);
	}
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Override
	public java.util.List<InvoiceDTO> getAllInvoice() {
		return invoiceRepository.findAll().stream().map(this::toDTO).toList();
	}

	@Override
	public java.util.List<InvoiceDTO> getTodayInvoice() {
		return invoiceRepository.findTodayInvoices().stream().map(this::toDTO).toList();
	}

	@Override
	public void updateInvoiceStatus(Long id, Integer status) {
		Invoice invoice = invoiceRepository.findById(id).orElse(null);
		if (invoice != null) {
			invoice.setStatus(status);
			invoiceRepository.save(invoice);
		} else {
			System.out.println("Invoice with id " + id + " does not exist.");
		}
	}

	@Override
	public InvoiceDTO getInvoiceById(Long id) {
		Invoice invoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy Invoice với id: "+ id));
		return toDTO(invoice);
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 24e6ed3616444da2659de8f5147ef376a3049ed9
