package poly.quanlyquanao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.dto.InvoiceDetailDTO;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.mapper.InvoiceDetailMapper;
import poly.quanlyquanao.mapper.InvoiceMapper;
import poly.quanlyquanao.repository.InvoiceDetailRepository;
import poly.quanlyquanao.repository.InvoiceRepository;
import poly.quanlyquanao.service.Impl.IInvoiceDetailService;
@Service
public class InvoiceDetailService implements IInvoiceDetailService{
	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
		@Autowired
	InvoiceRepository invoiceRepository;
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
}