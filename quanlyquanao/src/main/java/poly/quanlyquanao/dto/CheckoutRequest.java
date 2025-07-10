package poly.quanlyquanao.dto;

import java.util.List;

public class CheckoutRequest {
    private InvoiceInfo invoiceInfo;
    private List<CartItem> items;
	public CheckoutRequest() {
	}
	public CheckoutRequest(InvoiceInfo invoiceInfo, List<CartItem> items) {
		super();
		this.invoiceInfo = invoiceInfo;
		this.items = items;
	}
	public InvoiceInfo getInvoiceInfo() {
		return invoiceInfo;
	}
	public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
		this.invoiceInfo = invoiceInfo;
	}
	public List<CartItem> getItems() {
		return items;
	}
	public void setItems(List<CartItem> items) {
		this.items = items;
	}

}
