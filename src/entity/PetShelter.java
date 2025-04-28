package entity;

import java.util.ArrayList;
import java.util.List;

public class PetShelter {
    private List<Pet> availablePets;

    // Constructor
    public PetShelter() {
        availablePets = new ArrayList<>();
    }

    // Method to add a pet
    public void addPet(Pet pet) {
        availablePets.add(pet);
        System.out.println(pet.getName() + " has been added to the shelter.");
    }

    // Method to remove a pet
    public void removePet(Pet pet) {
        if (availablePets.remove(pet)) {
            System.out.println(pet.getName() + " has been adopted and removed from the shelter.");
        } else {
            System.out.println("Pet not found in the shelter.");
        }
    }

    // Method to list all available pets
    public void listAvailablePets() {
        if (availablePets.isEmpty()) {
            System.out.println("No pets currently available for adoption.");
        } else {
            System.out.println("Available Pets for Adoption:");
            for (Pet pet : availablePets) {
                System.out.println(pet.toString());
            }
        }
    }

    // Getter for availablePets (Optional, in case needed outside)
    public List<Pet> getAvailablePets() {
        return availablePets;
    }
}

