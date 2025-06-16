package poly.quanlyquanao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String phonenumber;
    private String address;
    private String email;
    private LocalDateTime registrationdate;
    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;

    private Integer status;

    private String emailVerificationToken; // chuẩn bị cho chức năng Email Verification Token, hiện tại thuộc tính này chưa được sử dụng
    private Timestamp tokenCreationTime; // Thời gian tạo token, cài này giống cái trên
}