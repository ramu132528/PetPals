package entity;

public class Dog extends Pet {
    private String dogBreed;

    // Constructor
    public Dog(String name, int age, String breed, String dogBreed) {
        super(name, age, breed); // call to parent Pet constructor
        this.dogBreed = dogBreed;
    }

    // Default Constructor (Optional, for flexibility)
    public Dog() {
        super(); // call to parent default constructor
    }

    // Getter and Setter
    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    // toString() method
    @Override
    public String toString() {
        return "Dog{" +
                "Name='" + getName() + '\'' +
                ", Age=" + getAge() +
                ", Breed='" + getBreed() + '\'' +
                ", DogBreed='" + dogBreed + '\'' +
                '}';
    }
}

