package poly.quanlyquanao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.Warehouse;
import poly.quanlyquanao.repository.WarehouseRepository;
import poly.quanlyquanao.service.Impl.IWarehouseService;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    private WarehouseRepository _warehouseRepository; // Tiêm WarehouseRepository

    @Override
    public Warehouse getWarehouseById(Long id) { // Đã thay đổi kiểu tham số thành Long
        return _warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy kho với id: " + id));
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return _warehouseRepository.findAll();
    }

    @Override
    public Warehouse addWarehouse(Warehouse warehouse) {
        return _warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse updateWarehouse(Long id, Warehouse warehouse) {
        Optional<Warehouse> existingWarehouseOptional = _warehouseRepository.findById(id);
        if (existingWarehouseOptional.isPresent()) {
            Warehouse existingWarehouse = existingWarehouseOptional.get();
            // Cập nhật các thuộc tính của kho hiện có
            existingWarehouse.setWarehouseName(warehouse.getWarehouseName());
            existingWarehouse.setAddress(warehouse.getAddress());
            existingWarehouse.setStatus(warehouse.getStatus());

            return _warehouseRepository.save(existingWarehouse);
        }
        return null; // Trả về null nếu không tìm thấy kho để cập nhật
    }

    @Override
    public void deleteWarehouse(Long id) {
        if (!_warehouseRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy kho với id: " + id);
        }
        _warehouseRepository.deleteById(id);
    }
}