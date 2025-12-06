package attendance.ui;

import attendance.model.AttendanceRecord;
import attendance.service.StudentService;
import attendance.service.AttendanceService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class MainFrame extends JFrame {

    private final StudentService studentService;
    private final AttendanceService attendanceService;

    public MainFrame(StudentService studentService, AttendanceService attendanceService) {
        this.studentService = studentService;
        this.attendanceService = attendanceService;

        setTitle("Attendance System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton addStudentBtn = new JButton("Öğrenci Ekle");
        JButton createAttendanceBtn = new JButton("Yoklama Oluştur");
        JButton markPresentBtn = new JButton("Öğrenciyi VAR İşaretle");
        JButton showAttendanceBtn = new JButton("Tüm Yoklamaları Göster");

        panel.add(addStudentBtn);
        panel.add(createAttendanceBtn);
        panel.add(markPresentBtn);
        panel.add(showAttendanceBtn);

        add(panel, BorderLayout.CENTER);

        // --- EVENTLER ---
        addStudentBtn.addActionListener(e -> addStudentUI());
        createAttendanceBtn.addActionListener(e -> createAttendanceUI());
        markPresentBtn.addActionListener(e -> markPresentUI());
        showAttendanceBtn.addActionListener(e -> showAllAttendanceUI());
    }


    // -------------------- UI FUNCTIONS --------------------

    private void addStudentUI() {
        String name = JOptionPane.showInputDialog(this, "Öğrenci Adı:");
        String number = JOptionPane.showInputDialog(this, "Öğrenci Numarası:");

        if (name != null && number != null) {
            studentService.addStudent(name, number);
            JOptionPane.showMessageDialog(this, "Öğrenci eklendi!");
        }
    }

    private void createAttendanceUI() {
        String dateStr = JOptionPane.showInputDialog(this, "Tarih (YYYY-MM-DD):");

        if (dateStr != null) {
            attendanceService.createAttendance(LocalDate.parse(dateStr));
            JOptionPane.showMessageDialog(this, "Yoklama oluşturuldu!");
        }
    }

    private void markPresentUI() {
        String dateStr = JOptionPane.showInputDialog(this, "Tarih (YYYY-MM-DD):");
        String studentIdStr = JOptionPane.showInputDialog(this, "Öğrenci ID:");

        if (dateStr != null && studentIdStr != null) {
            attendanceService.markPresent(LocalDate.parse(dateStr), Integer.parseInt(studentIdStr));
            JOptionPane.showMessageDialog(this, "Öğrenci VAR olarak işaretlendi!");
        }
    }

    private void showAllAttendanceUI() {
        StringBuilder sb = new StringBuilder();

        for (AttendanceRecord record : attendanceService.getAllAttendance()) {
            sb.append("Tarih: ").append(record.getDate()).append("\n");
            sb.append("Var olan öğrenciler: ").append(record.getPresentStudentIds()).append("\n\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString());
    }
}
