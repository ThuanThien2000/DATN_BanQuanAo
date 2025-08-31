package poly.quanlyquanao.service.Impl;

import java.util.List;

import poly.quanlyquanao.dto.CartItem;
import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.dto.InvoiceInfo;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.dto.UserDTO;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.model.User;

public interface StaffCheckoutServiceImpl {
    public ProductDetailDTO getProductDetailDTOByPDCode(String code);
    public Invoice checkoutOrder(InvoiceInfo newInvoice, List<CartItem> items);
    public User getAccountInfo(String phoneNumber);
    public InvoiceDTO update(Invoice invoice);

}
