package poly.quanlyquanao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Warehouse")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "warehouse_name", nullable = false, unique = true)
    private String warehouseName;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String address;

    private Integer status;
}
