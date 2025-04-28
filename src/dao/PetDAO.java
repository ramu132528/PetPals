package dao;

import entity.Pet;

public interface PetDAO {
    void addPet(Pet pet);
    void listAvailablePets();
    void removePet(int petId);
    void listAllPets();
}

