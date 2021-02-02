package piktoproject.pikto.models;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String mobileNr;
    private String email;
    private String password;
    private int admin;
    private int seller;
    private String pictureUrl;

    public User() {
    }

    public int getUserID() {
        return userId;
    }

    public void setUserID(int userID) {
        this.userId = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNr() {
        return mobileNr;
    }

    public void setMobileNr(String mobileNr) {
        this.mobileNr = mobileNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
