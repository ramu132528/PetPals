package dao;

import entity.CashDonation;
import entity.Donation;
import entity.ItemDonation;
import util.DBConnUtil;
import config.AppConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DonationDAOImpl implements DonationDAO {

    @Override
    public void recordDonation(Donation donation) {
        String insertQuery = "INSERT INTO donations (donor_name, amount, type, donation_date, item_type) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnUtil.getConnection("db.properties");
             PreparedStatement ps = conn.prepareStatement(insertQuery)) {

            ps.setString(1, donation.getDonorName());
            ps.setDouble(2, donation.getAmount());

            if (donation instanceof CashDonation) {
                ps.setString(3, "Cash");
                ps.setDate(4, java.sql.Date.valueOf(((CashDonation) donation).getDonationDate()));
                ps.setNull(5, java.sql.Types.VARCHAR);
            } else if (donation instanceof ItemDonation) {
                ps.setString(3, "Item");
                ps.setNull(4, java.sql.Types.DATE);
                ps.setString(5, ((ItemDonation) donation).getItemType());
            }

            ps.executeUpdate();
            System.out.println("Donation recorded successfully!");

        } catch (SQLException e) {
            System.out.println("Error while recording donation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void listAllDonations() {
        String selectQuery = "SELECT * FROM donations";

        try (Connection conn = DBConnUtil.getConnection("db.properties");
             PreparedStatement ps = conn.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n=== All Donations ===");

            boolean donationsFound = false;

            while (rs.next()) {
                donationsFound = true;
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Donor: " + rs.getString("donor_name") +
                                " | Amount: $" + rs.getDouble("amount") +
                                " | Type: " + rs.getString("type")
                );
            }

            if (!donationsFound) {
                System.out.println("No donations found yet.");
            }

        } catch (SQLException e) {
            System.out.println("Error while listing donations: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
