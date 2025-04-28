package entity;

public class Cat extends Pet {
    private String catColor;

    // Constructor
    public Cat(String name, int age, String breed, String catColor) {
        super(name, age, breed); // call to parent Pet constructor
        this.catColor = catColor;
    }

    // Default Constructor (Optional, for flexibility)
    public Cat() {
        super(); // call to parent default constructor
    }

    // Getter and Setter
    public String getCatColor() {
        return catColor;
    }

    public void setCatColor(String catColor) {
        this.catColor = catColor;
    }

    // toString() method
    @Override
    public String toString() {
        return "Cat{" +
                "Name='" + getName() + '\'' +
                ", Age=" + getAge() +
                ", Breed='" + getBreed() + '\'' +
                ", CatColor='" + catColor + '\'' +
                '}';
    }
}
