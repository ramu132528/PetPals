package entity;

import java.time.LocalDate;

public class CashDonation extends Donation {
    private LocalDate donationDate;

    // Constructor
    public CashDonation(String donorName, double amount, LocalDate donationDate) {
        super(donorName, amount); // Call to parent Donation constructor
        this.donationDate = donationDate;
    }

    // Default Constructor (Optional)
    public CashDonation() {
        super();
    }

    // Getter and Setter
    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    // Implement abstract method
    @Override
    public void recordDonation() {
        System.out.println("Cash Donation Recorded: " + this.toString());
    }

    // toString() method
    @Override
    public String toString() {
        return "CashDonation{" +
                "DonorName='" + getDonorName() + '\'' +
                ", Amount=" + getAmount() +
                ", DonationDate=" + donationDate +
                '}';
    }
}
