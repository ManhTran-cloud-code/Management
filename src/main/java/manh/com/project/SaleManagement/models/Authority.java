package manh.com.project.SaleManagement.models;

public class Authority {
    private String email;
    private String authorityName;

    public Authority() {
    }

    public Authority(String email, String authorityName) {
        this.email = email;
        this.authorityName = authorityName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
}
