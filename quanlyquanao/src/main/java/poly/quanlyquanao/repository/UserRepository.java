package poly.quanlyquanao.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import poly.quanlyquanao.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
//    @Query(value = """
//    select 
//    username, password, fullname, phonenumber, address, email, registrationdate
//    from Users
//    """, nativeQuery = true)
//    List<Map<Long, Object>> findAllUser();

//    @Override
//    Page<User> findAll(Pageable pagealble);

    @Query("SELECT u from User u WHERE u.status = 1")
    List<User> findByStatusOne();

    Optional<User> findByUsername(String username);
}