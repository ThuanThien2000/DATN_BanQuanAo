package poly.quanlyquanao.service.Impl;

import poly.quanlyquanao.model.Inventory;

import java.util.List;

public interface IInventoryService {
    Inventory getInventoryById(Long id);
    List<Inventory> getAllInventorys();
    Inventory addInventory(Inventory inventory);
    Inventory updateInventory(Long id, Inventory inventory);
    void deleteInventory(Long id);
}