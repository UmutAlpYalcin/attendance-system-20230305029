package attendance.model;

public abstract class Person {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Polymorphism: Student ve Teacher bunu farklı şekilde gösterecek
    public abstract String getDisplayInfo();
}
