package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.Image;
import poly.quanlyquanao.service.ImageService;

import java.util.List;

@RestController
@RequestMapping("/api/product/{productId}/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

  //localhost:8080/api/product/1/image/all
    @GetMapping("/all")
    public ResponseEntity<List<Image>> getAll(@PathVariable("productId") Long productId) {
        List<Image> images = imageService.getAllByProductId(productId);
        return ResponseEntity.ok(images);
    }
    
  //localhost:8080/api/product/1/image/add
    @PostMapping("/add")
    public ResponseEntity<List<Image>> add(@PathVariable("productId") Long productId, @RequestBody List<Image> images) {
        List<Image> savedImages = imageService.addImages(productId, images);
        if (savedImages == null || savedImages.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(savedImages);
    }
    
  //localhost:8080/api/product/1/image/delete/5
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> softDeleteImage(@PathVariable("id") Long id) {
        boolean success = imageService.softDelete(id);
        if (!success) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Deleted successfully (soft delete)");
    }
}
