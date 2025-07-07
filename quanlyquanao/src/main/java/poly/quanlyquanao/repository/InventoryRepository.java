package poly.quanlyquanao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByWarehouseIdAndQuantityGreaterThan(Long warehouseId, int quantity);
    
    List<Inventory> findByStatus(int status);

    List<Inventory> findByWarehouseIdAndStatus(Long warehouseId, int status);

    List<Inventory> findByQuantityLessThanAndStatus(int threshold, int status);
    @Query("SELECT i FROM Inventory i WHERE i.warehouse.id = :warehouseId AND i.quantity > 0 AND i.importDate < :thresholdDate")
    List<Inventory> findOldStockProducts(Long warehouseId, LocalDate thresholdDate);
} 