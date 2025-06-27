package poly.quanlyquanao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.Inventory;
import poly.quanlyquanao.repository.InventoryRepository;
import poly.quanlyquanao.service.Impl.IInventoryService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService implements IInventoryService {
    @Autowired
    private InventoryRepository _inventoryRepository; // Tiêm InventoryRepository

    @Override
    public Inventory getInventoryById(Long id) {
        return _inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tồn kho với id: " + id));
    }

    @Override
    public List<Inventory> getAllInventorys() {
        return _inventoryRepository.findAll();
    }

    @Override
    public Inventory addInventory(Inventory inventory) {
        // Đặt thời gian cập nhật lần cuối khi thêm mới
        inventory.setLastUpdatedAt(LocalDateTime.now());
        return _inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateInventory(Long id, Inventory inventory) {
        Optional<Inventory> existingInventoryOptional = _inventoryRepository.findById(id);
        if (existingInventoryOptional.isPresent()) {
            Inventory existingInventory = existingInventoryOptional.get();
            // Cập nhật các thuộc tính của tồn kho hiện có
            existingInventory.setProductDetail(inventory.getProductDetail());
            existingInventory.setWarehouse(inventory.getWarehouse());
            existingInventory.setQuantity(inventory.getQuantity());
            existingInventory.setStatus(inventory.getStatus());
            existingInventory.setLastUpdatedAt(LocalDateTime.now()); // Cập nhật thời gian khi có sự thay đổi

            return _inventoryRepository.save(existingInventory);
        }
        return null; // Trả về null nếu không tìm thấy tồn kho để cập nhật
    }

    @Override
    public void deleteInventory(Long id) {
        if (!_inventoryRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy tồn kho với id: " + id);
        }
        _inventoryRepository.deleteById(id);
    }
}
