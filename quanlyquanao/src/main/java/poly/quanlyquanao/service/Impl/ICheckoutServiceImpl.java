package poly.quanlyquanao.service.Impl;

import java.util.List;

import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.model.InvoiceDetail;

public interface ICheckoutServiceImpl {
	public Invoice checkoutOrder(Invoice newInvoice, List<InvoiceDetail> invoiceDetailList);
}
