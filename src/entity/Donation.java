package entity;

public abstract class Donation {
    private String donorName;
    private double amount;

    // Constructor
    public Donation(String donorName, double amount) {
        this.donorName = donorName;
        this.amount = amount;
    }

    // Default Constructor (Optional)
    public Donation() {}

    // Getters and Setters
    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Abstract Method
    public abstract void recordDonation();

    // toString() method
    @Override
    public String toString() {
        return "Donation{" +
                "DonorName='" + donorName + '\'' +
                ", Amount=" + amount +
                '}';
    }
}
