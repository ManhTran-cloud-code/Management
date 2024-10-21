package manh.com.project.SaleManagement.models;

import java.sql.Date;

public class Sale {
    private Date orderDate;
    private Double totalMoney;

    public Sale() {
    }

    public Sale(Date orderDate, Double totalMoney) {
        this.orderDate = orderDate;
        this.totalMoney = totalMoney;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
