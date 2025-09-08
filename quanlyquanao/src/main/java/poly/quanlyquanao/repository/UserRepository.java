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

    // Tìm người dùng đang hoạt động (status = 1)
    @Query("SELECT u from User u WHERE u.status = 1")
    List<User> findByStatusOne();

    Optional<User> findByUsername(String username);
    Optional<User> findByEmailVerificationToken(String token);
    Optional<User> findByEmail(String email);
    Optional<User> findFirstByPhonenumberAndStatus(String phonenumber, Integer status);

    // Danh sách chỉ nhân viên đang hoạt động
    @Query("SELECT u from User u WHERE u.role.id = 2 AND u.status = 1")
    List<User> findStaff();
    // Danh sách chỉ khách hàng đang hoạt động
    @Query("SELECT u from User u WHERE u.role.id = 3 AND u.status = 1")
    List<User> findCustomer();

    // Chức năng tìm kiếm người dùng: họ tên, sđt, email của nhân viên và khách hàng.
    // Tìm kiếm nhân viên theo từ khóa
    @Query("""
        SELECT u FROM User u 
        WHERE u.role.id = 2 AND u.status = 1
            AND (
                LOWER(u.fullname) LIKE LOWER(CONCAT('%', :keyword, '%')) 
                OR LOWER(u.phonenumber) LIKE LOWER(CONCAT('%', :keyword, '%')) 
                OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
            )
    """)
    List<User> searchStaff(String keyword);

    // Tìm kiếm khách hàng theo từ khóa
    @Query("""
        SELECT u FROM User u 
        WHERE u.role.id = 3 AND u.status = 1
            AND (
                LOWER(u.fullname) LIKE LOWER(CONCAT('%', :keyword, '%')) 
                OR LOWER(u.phonenumber) LIKE LOWER(CONCAT('%', :keyword, '%')) 
                OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
            )
    """)
    List<User> searchCustomer(String keyword);

    // Thống kê số lượng người dùng gồm khách hàng và nhân viên
    @Query("SELECT COUNT(u) FROM User u WHERE u.role.id = 2 AND u.status = 1")
    long countActiveStaff();

    @Query("SELECT COUNT(u) FROM User u WHERE u.role.id = 3 AND u.status = 1")
    long countActiveCustomer();

}