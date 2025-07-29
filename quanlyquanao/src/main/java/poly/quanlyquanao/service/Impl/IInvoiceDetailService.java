package poly.quanlyquanao.service.Impl;

import java.util.List;

import jakarta.transaction.Transactional;
import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.dto.InvoiceDetailDTO;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.model.ProductDetail;
public interface IInvoiceDetailService {
	List<InvoiceDetailDTO> findAllInvoiceDetaiByInvoiceID(String invoiceCode);
	InvoiceDetailDTO getInvoiceDetailById(String invoiceCode, Long invoiceDetailId);
	InvoiceDTO getInvoiceByCode(String invoiceCode);
	Invoice updateInvoiceStatus(String invoiceCode, Integer status);
	Invoice updateInvoiceAddress(String invoiceCode, String deliveryAddress, String phonenumber);
	Invoice updatePaymentMethod(String invoiceCode);
    void userCancelInvoice(String invoiceCode);
}