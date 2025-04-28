package dao;

import entity.AdoptionEvent;
import util.DBConnUtil;
import config.AppConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdoptionEventDAOImpl implements AdoptionEventDAO {

    @Override
    public void hostEvent(AdoptionEvent event) {
        String insertQuery = "INSERT INTO adoption_events (event_name, event_date) VALUES (?, CURDATE())";

        try (Connection conn = DBConnUtil.getConnection("db.properties");
             PreparedStatement ps = conn.prepareStatement(insertQuery)) {

            ps.setString(1, event.getEventName());

            ps.executeUpdate();
            System.out.println("Adoption event hosted successfully!");

        } catch (SQLException e) {
            System.out.println("Error while hosting event: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void listParticipants(int eventId) {
        String selectQuery = "SELECT participant_name, participant_type FROM participants WHERE event_id = ?";

        try (Connection conn = DBConnUtil.getConnection("db.properties");
             PreparedStatement ps = conn.prepareStatement(selectQuery)) {

            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n=== Participants for Event ID " + eventId + " ===");

            boolean participantsFound = false;

            while (rs.next()) {
                participantsFound = true;
                System.out.println(
                        "Name: " + rs.getString("participant_name") +
                                " | Type: " + rs.getString("participant_type")
                );
            }

            if (!participantsFound) {
                System.out.println("No participants registered yet for this event.");
            }

        } catch (SQLException e) {
            System.out.println("Error while listing participants: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
