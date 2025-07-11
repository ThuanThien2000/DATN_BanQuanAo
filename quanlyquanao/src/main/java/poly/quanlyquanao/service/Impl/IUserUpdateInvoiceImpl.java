package poly.quanlyquanao.service.Impl;

import poly.quanlyquanao.model.Invoice;

public interface IUserUpdateInvoiceImpl {
	Invoice getInvoiceById(Long id);
	void userCancelInvoice(Long id);
}
