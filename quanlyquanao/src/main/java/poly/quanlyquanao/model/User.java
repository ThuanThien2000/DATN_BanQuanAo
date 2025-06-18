package poly.quanlyquanao.model;

import jakarta.persistence.*;

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

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String fullname;

    private String phonenumber;
    private String address;

    @Column(unique = true)
    private String email;

    private LocalDateTime registrationdate;

    @ManyToOne
    @JoinColumn(name = "roleid", nullable = false)
    private Role role;

    private String emailVerificationToken;
    private Timestamp tokenCreationTime;

    private Integer status;

    //    @Column(nullable = false)
    private Boolean gender;
    // Chính thức sửa lỗi
}