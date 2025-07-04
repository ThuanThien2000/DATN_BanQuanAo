package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.Inventory;
import poly.quanlyquanao.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    
    //Lấy toàn bộ tồn kho localhost:8080/api/inventory
    @GetMapping("/all")
    public List<Inventory> getAll() {
        return inventoryService.getAll();
    }

    
    //Lấy tồn kho đang hoạt động (status = 1) localhost:8080/api/inventory/active
    @GetMapping("/active")
    public List<Inventory> getAllActive() {
        return inventoryService.getAllActive();
    }

    
    //Lấy tồn kho theo ID localhost:8080/api/inventory/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getById(@PathVariable Long id) {
        Inventory inventory = inventoryService.getById(id);
        return inventory != null ? ResponseEntity.ok(inventory) : ResponseEntity.notFound().build();
    }

    
    // → Thêm mới tồn kho localhost:8080/api/inventory/create
    @PostMapping("/create")
    public Inventory create(@RequestBody Inventory inventory) {
        return inventoryService.create(inventory);
    }

    
    // → Cập nhật tồn kho localhost:8080/api/inventory/{id}
    @PutMapping("/update/{id}")
    public Inventory update(@PathVariable Long id, @RequestBody Inventory inventory) {
        return inventoryService.update(id, inventory);
    }

    
    //Soft delete (ẩn tồn kho) localhost:8080/api/inventory/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> softDelete(@PathVariable Long id) {
        inventoryService.softDelete(id);
        return ResponseEntity.ok().build();
    }

    
    // Lấy tồn kho theo kho cụ thể localhost:8080/api/inventory/warehouse/{warehouseId}
    @GetMapping("/warehouse/{warehouseId}")
    public List<Inventory> getByWarehouse(@PathVariable Long warehouseId) {
        return inventoryService.getByWarehouseId(warehouseId);
    }

    
    //Lấy các mặt hàng tồn kho dưới ngưỡng cảnh báo (mặc định 5) localhost:8080/api/inventory/low-stock?threshold=5
    @GetMapping("/low-stock")
    public List<Inventory> getLowStock(@RequestParam(defaultValue = "5") int threshold) {
        return inventoryService.getLowStock(threshold);
    }
}