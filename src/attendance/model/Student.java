package attendance.model;

public class Student extends Person {

    private String studentNumber;

    public Student(int id, String name, String studentNumber) {
        super(id, name);
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    @Override
    public String getDisplayInfo() {
        return "Student: " + getName() + " (" + studentNumber + ")";
    }
}
