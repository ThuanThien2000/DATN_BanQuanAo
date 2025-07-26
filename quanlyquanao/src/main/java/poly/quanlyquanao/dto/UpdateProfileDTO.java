package poly.quanlyquanao.dto;

public class UpdateProfileDTO {
    private String fullname;
    private Boolean gender;
    private String phonenumber;
    private String address;
    private String email;
    private String oldPassword;
    private String newPassword;

    public UpdateProfileDTO() {
    }

    public UpdateProfileDTO(String fullname, Boolean gender, String phonenumber, String address, String email, String oldPassword, String newPassword) {
        this.fullname = fullname;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.address = address;
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
