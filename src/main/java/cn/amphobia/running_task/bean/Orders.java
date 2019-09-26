package cn.amphobia.running_task.bean;

public class Orders {
    private String orderId;
    private String goodName;
    private String goodLocation;
    private String endTime;
    private String endAddress;
    private String money;
    private String telephone;
    private String createTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodLocation() {
        return goodLocation;
    }

    public void setGoodLocation(String goodLocation) {
        this.goodLocation = goodLocation;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId='" + orderId + '\'' +
                ", goodName='" + goodName + '\'' +
                ", goodLocation='" + goodLocation + '\'' +
                ", endTime='" + endTime + '\'' +
                ", endAddress='" + endAddress + '\'' +
                ", money='" + money + '\'' +
                ", telephone='" + telephone + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
