package poly.quanlyquanao.dto;

public class UserDTO {
    private String username;
    private String password;
    private String fullname;
    private Boolean gender;
    private String phonenumber;
    private String address;
    private String email;
    private Long roleId;
    private String roleName;
    private String emailVerificationToken;
    private Integer status;

    public UserDTO() {}

    public UserDTO(String username, String password, String fullname, Boolean gender, String phonenumber, String address, String email, Long roleId, String roleName, String emailVerificationToken, Integer status) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.address = address;
        this.email = email;
        this.roleId = roleId;
        this.roleName = roleName;
        this.emailVerificationToken = emailVerificationToken;
        this.status = status;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public Boolean getGender() { return gender; }
    public void setGender(Boolean gender) { this.gender = gender; }
    public String getPhonenumber() { return phonenumber; }
    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
    public String getEmailVerificationToken() { return emailVerificationToken; }
    public void setEmailVerificationToken(String emailVerificationToken) { this.emailVerificationToken = emailVerificationToken; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
