package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.Inventory;
import poly.quanlyquanao.model.Warehouse;
import poly.quanlyquanao.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;
    
    //Lấy tất cả kho (bao gồm cả bị ẩn hay còn gọi là hết hàng) localhost:8080/api/warehouse/all
    @GetMapping("/all") 
    public List<Warehouse> getAll() {
        return warehouseService.getAll();
    }
    
    //Lấy danh sách kho đang hoạt động (status = 1, tức là còn hàng) localhost:8080/api/warehouse/active 
    @GetMapping("/active")
    public List<Warehouse> getAllActive() {
        return warehouseService.getAllActive();
    }
    
    //Lấy kho theo ID localhost:8080/api/warehouse/{id}
    @GetMapping("/{id}") 
    public ResponseEntity<Warehouse> getById(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.getById(id);
        return warehouse != null ? ResponseEntity.ok(warehouse) : ResponseEntity.notFound().build();
    }
    
    //Thêm mới kho localhost:8080/api/warehouse/create
    @PostMapping("/create")
    public Warehouse create(@RequestBody Warehouse warehouse) {
        return warehouseService.create(warehouse);
    }
    
    //Cập nhật thông tin kho theo ID localhost:8080/api/warehouse/update/{id}
    @PutMapping("/update/{id}")
    public Warehouse update(@PathVariable Long id, @RequestBody Warehouse warehouse) {
        return warehouseService.update(id, warehouse);
    }
    
    //Ẩn kho hàng (soft delete: status = 0)  localhost:8080/api/warehouse/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> softDelete(@PathVariable Long id) {
        warehouseService.softDelete(id);
        return ResponseEntity.ok().build();
    }
    
    //Lấy danh sách hàng tồn kho trong kho (quantity > 0) localhost:8080/api/warehouse/inventory/{id}
    @GetMapping("/inventory/{id}")
    public List<Inventory> getInventoryByWarehouse(@PathVariable Long id) {
        return warehouseService.getInventoryByWarehouse(id);
    }
    
    //Lấy hàng tồn nhập lâu hơn 90 ngày (hoặc tùy số ngày) localhost:8080/api/warehouse/old-stock?days=90/{id}
    @GetMapping("/old-stock/{id}")
    public List<Inventory> getOldStockProducts(@PathVariable Long id, @RequestParam(defaultValue = "90") int days) {
        return warehouseService.getOldStockProducts(id, days);
    }
}