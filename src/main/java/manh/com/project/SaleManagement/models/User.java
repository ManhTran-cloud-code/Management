package manh.com.project.SaleManagement.models;

public class User {
    private int id;
    private String name;
    private String email;
    private String encryptPassword;

    public User(int id, String name, String email, String encryptPassword) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.encryptPassword = encryptPassword;
    }
    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }
}
