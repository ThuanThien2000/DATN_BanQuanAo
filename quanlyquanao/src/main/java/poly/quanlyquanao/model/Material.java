package poly.quanlyquanao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "material")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "material_name")
    private String materialName;
    private String description;
    private Integer status;
}
