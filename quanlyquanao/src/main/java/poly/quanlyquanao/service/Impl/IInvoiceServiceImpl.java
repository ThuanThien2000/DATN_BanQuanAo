package poly.quanlyquanao.service.Impl;

import java.util.List;

import poly.quanlyquanao.model.Invoice;

public interface IInvoiceServiceImpl {
	List<Invoice> getAllInvoice();
	List<Invoice> getTodayInvoice();
	Invoice getInvoiceById(Long id);
	void updateInvoiceStatus(Long id, Integer status);
}