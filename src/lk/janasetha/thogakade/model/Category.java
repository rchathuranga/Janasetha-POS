package lk.janasetha.thogakade.model;

public class Category {
    private int cateId;
    private String description;
    private String status;

    public Category() {
    }

    public Category(String description, String status) {
        this.description = description;
        this.status = status;
    }

    public Category(int cateId, String description, String status) {
        this.cateId = cateId;
        this.description = description;
        this.status = status;
    }

    public int getCateId() {
        return cateId;
    }
    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cateId=" + cateId +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
