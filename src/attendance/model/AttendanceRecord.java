package attendance.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class AttendanceRecord {

    private LocalDate date;
    private Set<Integer> presentStudentIds = new HashSet<>();

    public AttendanceRecord(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void markPresent(int studentId) {
        presentStudentIds.add(studentId);
    }

    public boolean isPresent(int studentId) {
        return presentStudentIds.contains(studentId);
    }

    public Set<Integer> getPresentStudentIds() {
        return presentStudentIds;
    }
}
