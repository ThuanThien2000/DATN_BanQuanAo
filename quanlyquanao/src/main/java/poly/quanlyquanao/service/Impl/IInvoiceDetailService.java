package poly.quanlyquanao.service.Impl;

import java.util.List;

import poly.quanlyquanao.model.InvoiceDetail; 
public interface IInvoiceDetailService {
//	List<InvoiceDetail> findAllInvoiceDetaiByProductID(Long productID); // Đánh dấu sai code
	InvoiceDetail getInvoiceDetailById(Long id);
	void solfDeleteInvoiceDetail(Long id);
}
