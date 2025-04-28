package dao;

import entity.Donation;

public interface DonationDAO {
    void recordDonation(Donation donation);
    void listAllDonations();
}


