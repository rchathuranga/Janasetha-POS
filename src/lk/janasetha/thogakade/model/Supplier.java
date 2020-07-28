package lk.janasetha.thogakade.model;

public class Supplier {
    private int supplierId;
    private String supplier;
    private String description;
    private double buyingPricePercentage;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBuyingPricePercentage() {
        return buyingPricePercentage;
    }

    public void setBuyingPricePercentage(double buyingPricePercentage) {
        this.buyingPricePercentage = buyingPricePercentage;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplier='" + supplier + '\'' +
                ", description='" + description + '\'' +
                ", buyingPricePercentage=" + buyingPricePercentage +
                '}';
    }
}
