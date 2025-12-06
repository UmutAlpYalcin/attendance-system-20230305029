package attendance.service;

import attendance.model.Student;
import attendance.repository.InMemoryRepository;

import java.util.List;

public class StudentService {

    private final InMemoryRepository<Student, Integer> studentRepo;
    private int nextId = 1;

    public StudentService(InMemoryRepository<Student, Integer> studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student addStudent(String name, String studentNumber) {
        Student s = new Student(nextId++, name, studentNumber);
        studentRepo.save(s);
        return s;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
}
