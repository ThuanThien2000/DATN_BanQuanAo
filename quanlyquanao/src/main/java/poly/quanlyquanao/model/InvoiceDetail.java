/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.quanlyquanao.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "invoicedetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_detail_code")
    private String invoiceDetailCode;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "productdetail_id")
    private ProductDetail productDetail;

    private Integer quantity;
    private BigDecimal price;
    
    @Column(name = "totalprice", insertable = false, updatable = false)
    private BigDecimal totalPrice;
    
    private Integer status;
}
