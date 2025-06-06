
package poly.quanlyquanao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceCode;
    private String fullname;
    private String phonenumber;
    private String email;
    private String deliveryAddress;
    private String paymentMethod;
    private LocalDateTime creationDate;
    private Integer shippingFee;
    private Integer discountAmount;
    private BigDecimal totalAmount;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

}
