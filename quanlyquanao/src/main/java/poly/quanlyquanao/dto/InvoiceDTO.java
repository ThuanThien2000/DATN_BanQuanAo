package poly.quanlyquanao.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InvoiceDTO {
    private Long id;
    private String invoiceCode;
    private Long userId;
    private String fullname;
    private String phonenumber;
    private String email;
    private String deliveryAddress;
    private Long paymentMethodId;
    private LocalDateTime creationDate;
    private BigDecimal shippingFee;
    private Long voucherId;
    private BigDecimal discountAmount;
    private BigDecimal totalAmount;
    private Long assignedStaffId;
    private String imageFirstProduct;
    private Integer status;

    public InvoiceDTO() {}

    public InvoiceDTO(Long id, String invoiceCode, Long userId, String fullname, String phonenumber, String email, String deliveryAddress, Long paymentMethodId, LocalDateTime creationDate, BigDecimal shippingFee, Long voucherId, BigDecimal discountAmount, BigDecimal totalAmount, Long assignedStaffId, String imageFirstProduct, Integer status) {
        this.id = id;
        this.invoiceCode = invoiceCode;
        this.userId = userId;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethodId = paymentMethodId;
        this.creationDate = creationDate;
        this.shippingFee = shippingFee;
        this.voucherId = voucherId;
        this.discountAmount = discountAmount;
        this.totalAmount = totalAmount;
        this.assignedStaffId = assignedStaffId;
        this.imageFirstProduct = imageFirstProduct;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getInvoiceCode() { return invoiceCode; }
    public void setInvoiceCode(String invoiceCode) { this.invoiceCode = invoiceCode; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getPhonenumber() { return phonenumber; }
    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
    public Long getPaymentMethodId() { return paymentMethodId; }
    public void setPaymentMethodId(Long paymentMethodId) { this.paymentMethodId = paymentMethodId; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }
    public BigDecimal getShippingFee() { return shippingFee; }
    public void setShippingFee(BigDecimal shippingFee) { this.shippingFee = shippingFee; }
    public Long getVoucherId() { return voucherId; }
    public void setVoucherId(Long voucherId) { this.voucherId = voucherId; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public Long getAssignedStaffId() { return assignedStaffId; }
    public void setAssignedStaffId(Long assignedStaffId) { this.assignedStaffId = assignedStaffId; }
    public String getImageFirstProduct() { return imageFirstProduct; }
    public void setImageFirstProduct(String imageFirstProduct) { this.imageFirstProduct = imageFirstProduct; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    }
