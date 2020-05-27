package edu.ktu.guessthenumber;

public class PersonData {
    private int ID;
    private String name;
    private String difficulty;
    private int age;

    public PersonData()
    {
        this.ID = 0;
        this.name = "Name";
        this.difficulty = "0";
        this.age = 1;
    }

    public PersonData(int ID, String name, String difficulty, int age)
    {
        this.ID = ID;
        this.name = name;
        this.difficulty = difficulty;
        this.age = age;
    }

    public int getID() {
        return ID;
    }

    public String getName()
    {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
