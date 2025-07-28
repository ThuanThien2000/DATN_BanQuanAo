package poly.quanlyquanao.service.Impl;

import java.util.List;

import poly.quanlyquanao.dto.CartItem;
import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.dto.InvoiceInfo;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.dto.UserDTO;
import poly.quanlyquanao.model.Invoice;

public interface ICheckoutServiceImpl {
	public Invoice checkoutOrder(InvoiceInfo newInvoice, List<CartItem> items);
	public ProductDetailDTO getProductDetailDTOByPDCode(String code);
	public UserDTO getMyAccountInfo(String username);
	public InvoiceDTO update(Invoice invoice);
	public InvoiceDTO add(Invoice invoice);
}
