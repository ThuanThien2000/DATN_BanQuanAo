package poly.quanlyquanao.service.Impl;

import java.util.List;

import poly.quanlyquanao.dto.CartItem;
import poly.quanlyquanao.dto.InvoiceInfo;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.model.Invoice;

public interface ICheckoutServiceImpl {
	public Invoice checkoutOrder(InvoiceInfo newInvoice, List<CartItem> items);
	public ProductDetailDTO getProductDetailDTOByPDCode(String code);
}
