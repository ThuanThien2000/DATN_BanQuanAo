package poly.quanlyquanao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.quanlyquanao.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}