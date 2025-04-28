package entity;

public class Pet {
    private String name;
    private int age;
    private String breed;

    // Constructor
    public Pet(String name, int age, String breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    // Default Constructor (Optional, but useful)
    public Pet() {}

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    // toString() method
    @Override
    public String toString() {
        return "Pet{" +
                "Name='" + name + '\'' +
                ", Age=" + age +
                ", Breed='" + breed + '\'' +
                '}';
    }
}
