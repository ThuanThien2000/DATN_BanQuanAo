package poly.quanlyquanao.service.Impl;

import poly.quanlyquanao.model.Invoice;
//
public interface IUserUpdateInvoiceImpl {
	Invoice getInvoiceById(String invoiceCode);
	void userCancelInvoice(String invoiceCode);
}
