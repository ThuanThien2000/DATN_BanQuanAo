package poly.quanlyquanao.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Invoice")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class Invoice implements Serializable {

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public BigDecimal getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(BigDecimal shippingFee) {
		this.shippingFee = shippingFee;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public User getAssignedStaff() {
		return assignedStaff;
	}

	public void setAssignedStaff(User assignedStaff) {
		this.assignedStaff = assignedStaff;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}
public Invoice() {
	}
	public Invoice(Long id, String invoiceCode, User user, String fullname, String phonenumber, String email,
			String deliveryAddress, String description, PaymentMethod paymentMethod, LocalDateTime creationDate,
			BigDecimal shippingFee, Voucher voucher, BigDecimal discountAmount, BigDecimal totalAmount,
			User assignedStaff, Integer status, Set<InvoiceDetail> invoiceDetails) {
		super();
		this.id = id;
		this.invoiceCode = invoiceCode;
		this.user = user;
		this.fullname = fullname;
		this.phonenumber = phonenumber;
		this.email = email;
		this.deliveryAddress = deliveryAddress;
		this.description = description;
		this.paymentMethod = paymentMethod;
		this.creationDate = creationDate;
		this.shippingFee = shippingFee;
		this.voucher = voucher;
		this.discountAmount = discountAmount;
		this.totalAmount = totalAmount;
		this.assignedStaff = assignedStaff;
		this.status = status;
		this.invoiceDetails = invoiceDetails;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_code", nullable = false, unique = true, length = 50)
    private String invoiceCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String fullname;

    @Column(nullable = false, length = 20)
    private String phonenumber;

    @Column(length = 120)
    private String email;

    @Column(name = "delivery_address", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String deliveryAddress;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;
    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "shipping_fee", nullable = false, precision = 18, scale = 2)
    private BigDecimal shippingFee = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @Column(name = "discount_amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "total_amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;

    @ManyToOne // Bổ sung mối quan hệ cho assigned_staff_id
    @JoinColumn(name = "assigned_staff_id") // Có thể null
    private User assignedStaff; // Dùng tên khác để phân biệt với 'user' là khách hàng

    @Column(nullable = false)
    private Integer status;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InvoiceDetail> invoiceDetails;

}