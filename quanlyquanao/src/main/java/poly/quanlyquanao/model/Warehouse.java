package poly.quanlyquanao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Warehouse")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "warehouse_name", nullable = false, unique = true)
    private String warehouseName;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String address;

    private Integer status;

    public Warehouse(String warehouseName, String address, Integer status) {
        this.warehouseName = warehouseName;
        this.address = address;
        this.status = status;
    }
    public Warehouse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
