package dao;

import entity.AdoptionEvent;
import util.DBConnUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdoptionEventDAOImpl implements AdoptionEventDAO {

    private static final Logger LOGGER = Logger.getLogger(AdoptionEventDAOImpl.class.getName());

    @Override
    public void hostEvent(AdoptionEvent event) {
        String insertQuery = "INSERT INTO adoption_events (event_name, event_date) VALUES (?, ?)";

        Connection conn = DBConnUtil.getConnection("db.properties");

        if (conn == null) {
            LOGGER.severe("Failed to establish database connection.");
            return;
        }

        try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
            ps.setString(1, event.getEventName());
            ps.setDate(2, Date.valueOf(event.getEventDate()));

            ps.executeUpdate();
            LOGGER.info("Adoption event hosted successfully!");

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while hosting event: " + e.getMessage(), e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing connection: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public void listParticipants(int eventId) {
        String selectQuery = "SELECT participant_name, participant_type FROM participants WHERE event_id = ?";

        Connection conn = DBConnUtil.getConnection("db.properties");

        if (conn == null) {
            LOGGER.severe("Failed to establish database connection.");
            return;
        }

        try (PreparedStatement ps = conn.prepareStatement(selectQuery)) {
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
            LOGGER.log(Level.SEVERE, "Error while listing participants: " + e.getMessage(), e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing connection: " + e.getMessage(), e);
            }
        }
    }
}
