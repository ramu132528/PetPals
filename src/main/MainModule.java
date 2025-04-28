package main;

import dao.*;
import entity.*;
import exception.*;
import util.*;

import java.time.LocalDate;
import java.util.Scanner;

public class MainModule {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== PetPals Menu ===");
            System.out.println("1. List Available Pets");
            System.out.println("2. Add New Pet");
            System.out.println("3. Remove Pet by ID");
            System.out.println("4. Make a Donation");
            System.out.println("5. List All Donations");
            System.out.println("6. Host Adoption Event");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        listPets();
                        break;
                    case 2:
                        addPet();
                        break;
                    case 3:
                        removePet();
                        break;
                    case 4:
                        makeDonation();
                        break;
                    case 5:
                        listDonations();
                        break;
                    case 6:
                        hostAdoptionEvent();
                        break;
                    case 7:
                        running = false;
                        System.out.println("Thank you for using PetPals!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void listPets() throws Exception {
        PetDAO petDAO = new PetDAOImpl();
        petDAO.listAvailablePets();
    }

    private static void addPet() throws Exception {
        System.out.print("Enter Pet Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Pet Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (age <= 0) {
            throw new InvalidPetAgeException("Age must be a positive integer.");
        }

        System.out.print("Enter Pet Breed: ");
        String breed = scanner.nextLine();

        System.out.print("Enter Type (Dog/Cat): ");
        String type = scanner.nextLine();

        Pet pet;
        if (type.equalsIgnoreCase("Dog")) {
            System.out.print("Enter Dog Breed: ");
            String dogBreed = scanner.nextLine();
            pet = new Dog(name, age, breed, dogBreed);
        } else if (type.equalsIgnoreCase("Cat")) {
            System.out.print("Enter Cat Color: ");
            String catColor = scanner.nextLine();
            pet = new Cat(name, age, breed, catColor);
        } else {
            throw new Exception("Invalid pet type entered.");
        }

        PetDAO petDAO = new PetDAOImpl();
        petDAO.addPet(pet);
    }

    private static void removePet() throws Exception {
        System.out.print("Enter Pet ID to remove: ");
        int petId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        PetDAO petDAO = new PetDAOImpl();
        petDAO.removePet(petId);
    }

    private static void makeDonation() throws Exception {
        System.out.print("Enter Donor Name: ");
        String donorName = scanner.nextLine();

        System.out.print("Enter Donation Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        if (amount < config.AppConfig.MINIMUM_DONATION_AMOUNT) {
            throw new InsufficientFundsException("Minimum donation amount is $" + config.AppConfig.MINIMUM_DONATION_AMOUNT);
        }

        System.out.print("Is this a Cash or Item donation? ");
        String type = scanner.nextLine();

        Donation donation;
        if (type.equalsIgnoreCase("Cash")) {
            System.out.print("Enter Donation Date (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();
            LocalDate donationDate = LocalDate.parse(dateInput);

            donation = new CashDonation(donorName, amount, donationDate);
        } else if (type.equalsIgnoreCase("Item")) {
            System.out.print("Enter Item Type: ");
            String itemType = scanner.nextLine();
            donation = new ItemDonation(donorName, amount, itemType);
        } else {
            throw new Exception("Invalid donation type entered.");
        }

        DonationDAO donationDAO = new DonationDAOImpl();
        donationDAO.recordDonation(donation);
    }

    private static void listDonations() throws Exception {
        DonationDAO donationDAO = new DonationDAOImpl();
        donationDAO.listAllDonations();
    }

    private static void hostAdoptionEvent() throws Exception {
        System.out.print("Enter Event Name: ");
        String eventName = scanner.nextLine();

        AdoptionEvent event = new AdoptionEvent(eventName);
        AdoptionEventDAO eventDAO = new AdoptionEventDAOImpl();
        eventDAO.hostEvent(event);
    }
}
