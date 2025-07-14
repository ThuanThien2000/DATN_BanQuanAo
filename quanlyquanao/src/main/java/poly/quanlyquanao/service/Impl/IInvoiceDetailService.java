package poly.quanlyquanao.service.Impl;

import java.util.List;

import poly.quanlyquanao.dto.InvoiceDetailDTO;
public interface IInvoiceDetailService {
	List<InvoiceDetailDTO> findAllInvoiceDetaiByInvoiceID(Long invoiceID);
	InvoiceDetailDTO getInvoiceDetailById(Long invoiceId, Long invoiceDetailId);
}