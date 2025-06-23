package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.Inventory;
import poly.quanlyquanao.service.Impl.IInventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryRestController {
    @Autowired
    private IInventoryService inventoryService; // Tiêm IInventoryService

    /**
     * Lấy tất cả các mục tồn kho.
     * @return Danh sách các mục tồn kho.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Inventory>> getAllInventorys() {
        List<Inventory> inventorys = inventoryService.getAllInventorys();
        return new ResponseEntity<>(inventorys, HttpStatus.OK);
    }

    /**
     * Lấy mục tồn kho theo ID.
     * @param id ID của mục tồn kho.
     * @return Mục tồn kho hoặc HttpStatus.NOT_FOUND nếu không tìm thấy.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable("id") Long id) {
        try {
            Inventory inventory = inventoryService.getInventoryById(id);
            return new ResponseEntity<>(inventory, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Thêm mục tồn kho mới.
     * @param inventory Đối tượng Inventory được gửi từ frontend.
     * @return Mục tồn kho đã được tạo với ID và HttpStatus.CREATED.
     */
    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        try {
            Inventory newInventory = inventoryService.addInventory(inventory);
            return new ResponseEntity<>(newInventory, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Cập nhật mục tồn kho theo ID.
     * @param id ID của mục tồn kho cần cập nhật (từ URL path).
     * @param inventory Đối tượng Inventory chứa thông tin cập nhật (từ Request Body).
     * @return Mục tồn kho đã được cập nhật và HttpStatus.OK, hoặc HttpStatus.NOT_FOUND nếu không tìm thấy.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable("id") Long id, @RequestBody Inventory inventory) {
        Inventory updatedInventory = inventoryService.updateInventory(id, inventory);
        if (updatedInventory != null) {
            return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Xóa mục tồn kho theo ID.
     * @param id ID của mục tồn kho cần xóa.
     * @return ResponseEntity với thông báo thành công/thất bại và HttpStatus tương ứng.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable("id") Long id) { // Thay đổi kiểu trả về thành ResponseEntity<String>
        try {
            inventoryService.deleteInventory(id);
            return new ResponseEntity<>("Đã xóa mục tồn kho thành công!!!", HttpStatus.OK); // 200 OK với thông báo
        } catch (RuntimeException e) {
            // Khi không tìm thấy mục tồn kho, Service ném RuntimeException với thông báo
            return new ResponseEntity<>("Không tìm thấy mục tồn kho với id: " + id + " để xóa!", HttpStatus.NOT_FOUND); // 404 Not Found với thông báo lỗi
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Đã xảy ra lỗi khi xóa mục tồn kho: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
}