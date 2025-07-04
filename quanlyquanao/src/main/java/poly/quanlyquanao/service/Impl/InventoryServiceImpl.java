package poly.quanlyquanao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.Inventory;
import poly.quanlyquanao.repository.InventoryRepository;
import poly.quanlyquanao.service.InventoryService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> getAllActive() {
        return inventoryRepository.findByStatus(1);
    }

    @Override
    public Inventory getById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    @Override
    public Inventory create(Inventory inventory) {
        inventory.setStatus(1);
        inventory.setLastUpdatedAt(LocalDateTime.now());
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory update(Long id, Inventory updated) {
        Inventory exist = inventoryRepository.findById(id).orElse(null);
        if (exist != null) {
            exist.setProductDetail(updated.getProductDetail());
            exist.setWarehouse(updated.getWarehouse());
            exist.setQuantity(updated.getQuantity());
            exist.setLastUpdatedAt(LocalDateTime.now());
            return inventoryRepository.save(exist);
        }
        return null;
    }

    @Override
    public void softDelete(Long id) {
        Inventory exist = inventoryRepository.findById(id).orElse(null);
        if (exist != null) {
            exist.setStatus(0);
            exist.setLastUpdatedAt(LocalDateTime.now());
            inventoryRepository.save(exist);
        }
    }

    @Override
    public List<Inventory> getByWarehouseId(Long warehouseId) {
        return inventoryRepository.findByWarehouseIdAndStatus(warehouseId, 1);
    }

    @Override
    public List<Inventory> getLowStock(int threshold) {
        return inventoryRepository.findByQuantityLessThanAndStatus(threshold, 1);
    }
}