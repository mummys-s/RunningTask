package cn.amphobia.running_task.bean;

public class Address {
    private String id;
    private String address;
    private String telephone;
    private String status;
    private String username;
    private String userTelephone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                ", userTelephone='" + userTelephone + '\'' +
                '}';
    }
}
