package poly.quanlyquanao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.quanlyquanao.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
}
