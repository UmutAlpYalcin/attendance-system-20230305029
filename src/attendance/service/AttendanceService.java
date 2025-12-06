package attendance.service;

import attendance.model.AttendanceRecord;
import attendance.repository.InMemoryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AttendanceService {

    private final InMemoryRepository<AttendanceRecord, LocalDate> attendanceRepo;

    public AttendanceService(InMemoryRepository<AttendanceRecord, LocalDate> attendanceRepo) {
        this.attendanceRepo = attendanceRepo;
    }

    public void createAttendance(LocalDate date) {
        AttendanceRecord record = new AttendanceRecord(date);
        attendanceRepo.save(record);
    }

    public void markPresent(LocalDate date, int studentId) {
        Optional<AttendanceRecord> record = attendanceRepo.findById(date);
        record.ifPresent(r -> r.markPresent(studentId));
    }

    public List<AttendanceRecord> getAllAttendance() {
        return attendanceRepo.findAll();
    }
}
