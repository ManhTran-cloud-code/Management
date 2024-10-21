package manh.com.project.SaleManagement.models;

import java.sql.Date;

public class Discount {
    private int discountId;
    private String discountName;
    private int discountType;
    private Date startDate;
    private Date endDate;

    public Discount() {
    }

    public Discount(int discountId, String discountName, int discountType, Date startDate, Date endDate) {
        this.discountId = discountId;
        this.discountName = discountName;
        this.discountType = discountType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getDiscountType() {
        return discountType;
    }

    public void setDiscountType(int discountType) {
        this.discountType = discountType;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
