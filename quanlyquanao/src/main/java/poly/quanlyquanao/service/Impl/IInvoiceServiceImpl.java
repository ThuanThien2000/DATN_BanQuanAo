package poly.quanlyquanao.service.Impl;

import java.util.List;

import poly.quanlyquanao.dto.InvoiceDTO;

public interface IInvoiceServiceImpl {
	List<InvoiceDTO> getAllInvoice();
	InvoiceDTO getInvoiceById(Long id);
	void updateInvoiceStatus(Long id, Integer status);
}