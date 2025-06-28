package poly.quanlyquanao.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
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

    @Column(nullable = false)
    private Boolean gender;

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

    public User(String username, String password, String fullname, Boolean gender, String phonenumber, String address, String email, LocalDateTime registrationdate, Role role, String emailVerificationToken, Timestamp tokenCreationTime, Integer status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.address = address;
        this.email = email;
        this.registrationdate = registrationdate;
        this.role = role;
        this.emailVerificationToken = emailVerificationToken;
        this.tokenCreationTime = tokenCreationTime;
        this.status = status;
    }
    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(LocalDateTime registrationdate) {
        this.registrationdate = registrationdate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public Timestamp getTokenCreationTime() {
        return tokenCreationTime;
    }

    public void setTokenCreationTime(Timestamp tokenCreationTime) {
        this.tokenCreationTime = tokenCreationTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}