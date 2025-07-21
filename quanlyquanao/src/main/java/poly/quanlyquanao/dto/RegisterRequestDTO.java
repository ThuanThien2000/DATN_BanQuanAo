package poly.quanlyquanao.dto;

public class RegisterRequestDTO {
    private String username;
    private String password;
    private String fullname;
    private boolean gender;
    private String phonenumber;
    private String address;
    private String email;

    public RegisterRequestDTO(String username, String password, String fullname, boolean gender, String phonenumber, String address, String email) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.address = address;
        this.email = email;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
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
}
