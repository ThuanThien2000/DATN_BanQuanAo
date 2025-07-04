package poly.quanlyquanao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.quanlyquanao.model.Warehouse;
import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findByStatus(int status);
}