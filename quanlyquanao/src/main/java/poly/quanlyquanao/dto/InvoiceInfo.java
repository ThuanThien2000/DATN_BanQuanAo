package poly.quanlyquanao.dto;

import java.math.BigDecimal;

public class InvoiceInfo {
    private String fullname;

    private String phonenumber;
    private String email;
    private String deliveryAddress;
    private Long paymentMethodId;
    private Long voucherId;
    private Long assignedStaffId;
    private String description;
	public InvoiceInfo() {
	}
	public InvoiceInfo(String fullname, String phonenumber, String email, String deliveryAddress, Long paymentMethodId,
			Long voucherId, Long assignedStaffId, String description, BigDecimal shippingFee) {
		super();
		this.fullname = fullname;
		this.phonenumber = phonenumber;
		this.email = email;
		this.deliveryAddress = deliveryAddress;
		this.paymentMethodId = paymentMethodId;
		this.voucherId = voucherId;
		this.assignedStaffId = assignedStaffId;
		this.description = description;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public Long getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(Long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	public Long getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}
	public Long getAssignedStaffId() {
		return assignedStaffId;
	}
	public void setAssignedStaffId(Long assignedStaffId) {
		this.assignedStaffId = assignedStaffId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}

