package elibrary.project.com.elibrary.data.models;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("id")
    public int id;

    @SerializedName("firstname")
    public String firstname;

    @SerializedName("lastname")
    public String lastname;

    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("usertype")
    public int usertype;

    @SerializedName("status")
    public int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
