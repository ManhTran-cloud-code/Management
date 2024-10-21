package manh.com.project.SaleManagement.models;

public class DiscountDetail {
    private int id;
    private int discountId;
    private int productId;
    private int categoryId;

    public DiscountDetail() {
    }

    public DiscountDetail(int id, int discountId, int productId,int categoryId) {
        this.id = id;
        this.discountId = discountId;
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
