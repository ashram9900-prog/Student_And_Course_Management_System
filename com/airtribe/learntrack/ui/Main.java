package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.StatusNotActiveException;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);
        boolean running = true;
        while (running) {
            try {
                System.out.println("\n===== Student and Course Management System =====");
                System.out.println("1. Add new student");
                System.out.println("2. View all students");
                System.out.println("3. Search student by ID");
                System.out.println("4. Deactivate a student");
                System.out.println("5. Add new course");
                System.out.println("6. View all courses");
                System.out.println("7. Activate/Deactivate a course");
                System.out.println("8. Enroll a student in a course");
                System.out.println("9. View enrollments for a student");
                System.out.println("10. Mark enrollment as completed/cancelled");
                System.out.println("0. Exit");
                System.out.print("Select an option: ");
                String input = scanner.nextLine();
                switch (input) {
                    case "1":
                        System.out.print("First name: ");
                        String fn = scanner.nextLine();
                        System.out.print("Last name: ");
                        String ln = scanner.nextLine();
                        System.out.print("Email (optional, press Enter to skip): ");
                        String email = scanner.nextLine();
                        System.out.print("Batch: ");
                        String batch = scanner.nextLine();
                        if (email.isEmpty()) {
                            studentService.addStudent(fn, ln, batch);
                        } else {
                            int id = Student.getNextStudentId();
                            studentService.addStudent(new Student(id, fn, ln, email, batch, true));
                        }
                        System.out.println("Student added.");
                        break;
                    case "2":
                        List<Student> students = studentService.listStudents();
                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            for (Student s : students) {
                                System.out.println(s);
                            }
                        }
                        break;
                    case "3":
                        System.out.print("Enter student ID: ");
                        int sid = IdGenerator.parseIntOrDefault(scanner.nextLine(), -1);
                        if (sid == -1) break;
                        try {
                            Student s = studentService.getStudentById(sid);
                            System.out.println(s);
                        } catch (EntityNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "4":
                        System.out.print("Enter student ID to deactivate: ");
                        int did = IdGenerator.parseIntOrDefault(scanner.nextLine(), -1);
                        if (did == -1) break;
                        try {
                            studentService.deactivateStudent(did);
                            System.out.println("Student deactivated.");
                        } catch (EntityNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "5":
                        System.out.print("Course name: ");
                        String cname = scanner.nextLine();
                        System.out.print("Description: ");
                        String desc = scanner.nextLine();
                        System.out.print("Duration in weeks: ");
                        int dur = IdGenerator.parseIntOrDefault(scanner.nextLine(), -1);
                        if (dur == -1) break;
                        courseService.addCourse(cname, desc, dur);
                        System.out.println("Course added.");
                        break;
                    case "6":
                        List<Course> courses = courseService.getAllCourses();
                        if (courses.isEmpty()) {
                            System.out.println("No courses found.");
                        } else {
                            for (Course c : courses) {
                                System.out.println(c);
                            }
                        }
                        break;
                    case "7":
                        System.out.print("Enter course ID: ");
                        int cid = IdGenerator.parseIntOrDefault(scanner.nextLine(), -1);
                        if (cid == -1) break;
                        System.out.print("Activate (true) or Deactivate (false): ");
                        String act = scanner.nextLine();
                        boolean active = act.equalsIgnoreCase("true");
                        try {
                            courseService.setCourseActive(cid, active);
                            System.out.println("Course updated.");
                        } catch (EntityNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "8":
                        System.out.print("Enter student ID: ");
                        int esid = IdGenerator.parseIntOrDefault(scanner.nextLine(), -1);
                        if (esid == -1) break;
                        System.out.print("Enter course ID: ");
                        int ecid = IdGenerator.parseIntOrDefault(scanner.nextLine(), -1);
                        if (ecid == -1) break;
                        try {
                            Student estudent = studentService.getStudentById(esid);
                            Course ecourse = null;
                            for (Course c : courseService.getAllCourses()) {
                                if (c.getId() == ecid) {
                                    ecourse = c;
                                    break;
                                }
                            }
                            if (ecourse == null) {
                                throw new EntityNotFoundException("Course with ID " + ecid + " not found.");
                            }
                            enrollmentService.enrollStudent(esid, ecid);
                            System.out.println("Student enrolled in course.");
                        } catch (StatusNotActiveException e) {
                            System.out.println(e.getMessage());
                        } catch (EntityNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "9":
                        System.out.print("Enter student ID: ");
                        int vsid = IdGenerator.parseIntOrDefault(scanner.nextLine(), -1);
                        if (vsid == -1) break;
                        List<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(vsid);
                        if (enrollments.isEmpty()) {
                            System.out.println("No enrollments found for this student.");
                        } else {
                            for (Enrollment e : enrollments) {
                                System.out.println(e);
                            }
                        }
                        break;
                    case "10":
                        System.out.print("Enter enrollment ID: ");
                        int eid = IdGenerator.parseIntOrDefault(scanner.nextLine(), -1);
                        if (eid == -1) break;
                        System.out.print("Enter new status (ACTIVE, COMPLETED, CANCELLED): ");
                        String status = scanner.nextLine().toUpperCase();
                        Enrollment.Status newStatus;
                        try {
                            newStatus = Enrollment.Status.valueOf(status);
                        } catch (Exception ex) {
                            System.out.println("Invalid status.");
                            break;
                        }
                        try {
                            enrollmentService.updateEnrollmentStatus(eid, newStatus);
                            System.out.println("Enrollment status updated.");
                        } catch (EntityNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "0":
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception ex) {
                System.out.println("An error occurred: " + ex.getMessage());
            }
        }
        scanner.close();
    }
}
