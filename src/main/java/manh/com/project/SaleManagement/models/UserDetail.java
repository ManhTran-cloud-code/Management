package manh.com.project.SaleManagement.models;

import java.time.LocalDate;

public class UserDetail {
    private int userDetailId;
    private String phoneNumber;
    private String address;
    // 0 la nam 1 la nu 2 la gioi tinh khac
    private int gender;
    private LocalDate dateOfBirth;
    private int userId;

    public UserDetail() {
    }

    public UserDetail(int userDetailId, String phoneNumber, String address, int gender, LocalDate dateOfBirth, int userId) {
        this.userDetailId = userDetailId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.userId = userId;
    }

    public UserDetail(int userId) {
        this.userId = userId;
    }

    public int getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(int userDetailId) {
        this.userDetailId = userDetailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getUserId() {
        return userId;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
