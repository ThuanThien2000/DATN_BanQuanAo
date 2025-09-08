package poly.quanlyquanao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.quanlyquanao.model.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProductIdAndStatus(Long productId, int status);
}