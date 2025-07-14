package poly.quanlyquanao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.dto.InvoiceDetailDTO;
import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.repository.InvoiceDetailRepository;
import poly.quanlyquanao.service.Impl.IInvoiceDetailService;
@Service
public class InvoiceDetailService implements IInvoiceDetailService{
	private InvoiceDetailDTO toDTO(InvoiceDetail detail) {
		if (detail == null) return null;
		return new InvoiceDetailDTO(
			detail.getId(),
			detail.getInvoice() != null ? detail.getInvoice().getId() : null,
			detail.getProductDetail() != null ? detail.getProductDetail().getId() : null,
			detail.getQuantity(),
			detail.getPrice(),
			detail.getTotalPrice(),
			detail.getStatus()
		);
	}
	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
	@Override
	public List<InvoiceDetailDTO> findAllInvoiceDetaiByInvoiceID(Long invoiceID) {
		List<InvoiceDetail> details = invoiceDetailRepository.findAllByInvoice_IdAndStatus(invoiceID, 1);
		return details.stream().map(this::toDTO).toList();
	}

	@Override
	public InvoiceDetailDTO getInvoiceDetailById(Long invoiceId, Long invoiceDetailId) {
		InvoiceDetail detail = invoiceDetailRepository.findByInvoice_IdAndId(invoiceId, invoiceDetailId)
			.orElseThrow(() -> new RuntimeException("Không tìm thấy InvoiceDetail với id: "+ invoiceDetailId));
		return toDTO(detail);
	}
}