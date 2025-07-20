package poly.quanlyquanao.service.Impl;

import java.util.List;

import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.dto.InvoiceDetailDTO;
import poly.quanlyquanao.model.Invoice;
public interface IInvoiceDetailService {
	List<InvoiceDetailDTO> findAllInvoiceDetaiByInvoiceID(String invoiceCode);
	InvoiceDetailDTO getInvoiceDetailById(String invoiceCode, Long invoiceDetailId);
	InvoiceDTO getInvoiceByCode(String invoiceCode);
	Invoice updateInvoiceStatus(String invoiceCode, Integer status);
}