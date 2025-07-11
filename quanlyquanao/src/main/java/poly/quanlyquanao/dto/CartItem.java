package poly.quanlyquanao.dto;

public class CartItem {
    private Long productDetailId;
    private int quantity;
	public CartItem() {
	}
	public CartItem(Long productDetailId, int quantity) {
		super();
		this.productDetailId = productDetailId;
		this.quantity = quantity;
	}
	public Long getProductDetailId() {
		return productDetailId;
	}
	public void setProductDetailId(Long productDetailId) {
		this.productDetailId = productDetailId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
}
