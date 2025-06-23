package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.Warehouse;
import poly.quanlyquanao.service.Impl.IWarehouseService;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
@CrossOrigin(origins = "*")
public class WarehouseController {
    @Autowired
    private IWarehouseService warehouseService; // Tiêm IWarehouseService

    /**
     * Lấy tất cả các kho.
     * @return Danh sách các kho.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    /**
     * Lấy kho theo ID.
     * @param id ID của kho.
     * @return Kho hoặc HttpStatus.NOT_FOUND nếu không tìm thấy.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable("id") Long id) {
        try {
            Warehouse warehouse = warehouseService.getWarehouseById(id);
            return new ResponseEntity<>(warehouse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Thêm kho mới.
     * @param warehouse Đối tượng Warehouse được gửi từ frontend.
     * @return Kho đã được tạo với ID và HttpStatus.CREATED.
     */
    @PostMapping
    public ResponseEntity<Warehouse> addWarehouse(@RequestBody Warehouse warehouse) {
        try {
            Warehouse newWarehouse = warehouseService.addWarehouse(warehouse);
            return new ResponseEntity<>(newWarehouse, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Cập nhật kho theo ID.
     * @param id ID của kho cần cập nhật (từ URL path).
     * @param warehouse Đối tượng Warehouse chứa thông tin cập nhật (từ Request Body).
     * @return Kho đã được cập nhật và HttpStatus.OK, hoặc HttpStatus.NOT_FOUND nếu không tìm thấy.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable("id") Long id, @RequestBody Warehouse warehouse) {
        Warehouse updatedWarehouse = warehouseService.updateWarehouse(id, warehouse);
        if (updatedWarehouse != null) {
            return new ResponseEntity<>(updatedWarehouse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Xóa kho theo ID.
     * @param id ID của kho cần xóa.
     * @return ResponseEntity với thông báo thành công/thất bại và HttpStatus tương ứng.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable("id") Long id) { // Thay đổi kiểu trả về thành ResponseEntity<String>
        try {
            warehouseService.deleteWarehouse(id);
            return new ResponseEntity<>("Đã xóa kho thành công!!!", HttpStatus.OK); // 200 OK với thông báo
        } catch (RuntimeException e) {
            // Khi không tìm thấy kho, Service ném RuntimeException với thông báo
            return new ResponseEntity<>("Không tìm thấy kho với id: " + id + " để xóa!", HttpStatus.NOT_FOUND); // 404 Not Found với thông báo lỗi
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Đã xảy ra lỗi khi xóa kho: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
}