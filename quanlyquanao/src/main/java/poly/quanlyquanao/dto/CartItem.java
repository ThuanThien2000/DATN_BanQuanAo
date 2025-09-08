package poly.quanlyquanao.dto;

public class CartItem {
	private String productDetailCode;
	private int quantity;

	public CartItem() {
	}

	public CartItem(String productDetailCode, int quantity) {
		this.productDetailCode = productDetailCode;
		this.quantity = quantity;
	}

	public String getProductDetailCode() {
		return productDetailCode;
	}

	public void setProductDetailCode(String productDetailCode) {
		this.productDetailCode = productDetailCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
