package poly.quanlyquanao.service.Impl;

import java.util.List;

import poly.quanlyquanao.model.InvoiceDetail; 
public interface IInvoiceDetailService {
	List<InvoiceDetail> findAllInvoiceDetaiByInvoiceID(Long invoiceID); 
	InvoiceDetail getInvoiceDetailById(Long id);
}