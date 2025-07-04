package poly.quanlyquanao.service;

import poly.quanlyquanao.model.Inventory;

import java.util.List;

public interface InventoryService {
    List<Inventory> getAll();
    List<Inventory> getAllActive();
    Inventory getById(Long id);
    Inventory create(Inventory inventory);
    Inventory update(Long id, Inventory inventory);
    void softDelete(Long id);

    List<Inventory> getByWarehouseId(Long warehouseId);
    List<Inventory> getLowStock(int threshold);
}