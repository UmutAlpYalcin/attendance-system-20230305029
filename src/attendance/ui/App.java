package attendance.ui;

import attendance.model.Student;
import attendance.model.AttendanceRecord;
import attendance.repository.InMemoryRepository;
import attendance.service.StudentService;
import attendance.service.AttendanceService;

import javax.swing.*;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) {

        // Repository’leri oluştur
        InMemoryRepository<Student, Integer> studentRepo =
                new InMemoryRepository<>(Student::getId);

        InMemoryRepository<AttendanceRecord, LocalDate> attendanceRepo =
                new InMemoryRepository<>(AttendanceRecord::getDate);

        // Servisleri oluştur
        StudentService studentService = new StudentService(studentRepo);
        AttendanceService attendanceService = new AttendanceService(attendanceRepo);

        // UI frame başlat
        SwingUtilities.invokeLater(() -> new MainFrame(studentService, attendanceService).setVisible(true));
    }
}
