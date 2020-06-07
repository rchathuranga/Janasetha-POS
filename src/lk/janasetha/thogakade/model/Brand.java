package lk.janasetha.thogakade.model;

public class Brand {
    private int brandId;
    private String description;
    private String status;

    public Brand() {
    }

    public Brand(String description, String status) {
        this.description = description;
        this.status = status;
    }

    public Brand(int brandId, String description, String status) {
        this.brandId = brandId;
        this.description = description;
        this.status = status;
    }

    public int getBrandId() {
        return brandId;
    }
    public void setBrandId(int brandId) {
        this.brandId = brandId;
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
        return "Brand{" +
                "brandId=" + brandId +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
