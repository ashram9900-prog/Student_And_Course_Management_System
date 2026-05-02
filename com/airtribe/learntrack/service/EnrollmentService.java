package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.StatusNotActiveException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();
    private static int enrollmentIdCounter = 1;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId) throws EntityNotFoundException, StatusNotActiveException {
        Student student = studentService.getStudentById(studentId);
        if (!student.isActive()) {
            throw new StatusNotActiveException("Cannot enroll: Student with ID " + studentId + " is deactivated.");
        }
        Course course = null;
        for (Course c : courseService.getAllCourses()) {
            if (c.getId() == courseId) {
                course = c;
                break;
            }
        }
        if (course == null) {
            throw new EntityNotFoundException("Course with ID " + courseId + " not found.");
        }
        if (!course.isActive()) {
            throw new StatusNotActiveException("Cannot enroll: Course with ID " + courseId + " is deactivated.");
        }
        Enrollment enrollment = new Enrollment(
            enrollmentIdCounter++,
            studentId,
            courseId,
            LocalDate.now(),
            Enrollment.Status.ACTIVE
        );
        enrollments.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsForStudent(int studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) {
                result.add(e);
            }
        }
        return result;
    }

    public boolean updateEnrollmentStatus(int enrollmentId, Enrollment.Status status) throws EntityNotFoundException {
        for (Enrollment e : enrollments) {
            if (e.getId() == enrollmentId) {
                e.setStatus(status);
                return true;
            }
        }
        throw new EntityNotFoundException("Enrollment with ID " + enrollmentId + " not found.");
    }
}
