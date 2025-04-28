package dao;

import entity.Pet;
import util.DBConnUtil;
import config.AppConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PetDAOImpl implements PetDAO {

    @Override
    public void addPet(Pet pet) {
        String insertQuery = "INSERT INTO pets (name, age, breed, type) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnUtil.getConnection("db.properties");
             PreparedStatement ps = conn.prepareStatement(insertQuery)) {

            ps.setString(1, pet.getName());
            ps.setInt(2, pet.getAge());
            ps.setString(3, pet.getBreed());
            ps.setString(4, pet.getClass().getSimpleName());

            ps.executeUpdate();
            System.out.println("Pet added successfully!");

        } catch (SQLException e) {
            System.out.println("Error while adding pet: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void listAvailablePets() {
        listAllPets(); // Reuse
    }

    @Override
    public void removePet(int petId) {
        String deleteQuery = "DELETE FROM pets WHERE id = ?";

        try (Connection conn = DBConnUtil.getConnection("db.properties");
             PreparedStatement ps = conn.prepareStatement(deleteQuery)) {

            ps.setInt(1, petId);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Pet removed successfully!");
            } else {
                System.out.println("No pet found with ID: " + petId);
            }

        } catch (SQLException e) {
            System.out.println("Error while removing pet: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void listAllPets() {
        String selectQuery = "SELECT * FROM pets";

        try (Connection conn = DBConnUtil.getConnection("db.properties");
             PreparedStatement ps = conn.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n=== All Pets (Admin View) ===");

            boolean petsFound = false;

            while (rs.next()) {
                petsFound = true;
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Name: " + rs.getString("name") +
                                " | Age: " + rs.getInt("age") +
                                " years | Breed: " + rs.getString("breed") +
                                " | Type: " + rs.getString("type")
                );
            }

            if (!petsFound) {
                System.out.println("No pets available currently.");
            }

        } catch (SQLException e) {
            System.out.println("Error while listing all pets: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
