package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addStudent(int id, String firstName, String lastName, String email, String batch, boolean active) {
        Student student = new Student(id, firstName, lastName, email, batch, active);
        addStudent(student);
    }

    public void addStudent(String firstName, String lastName, String batch) {
        int id = Student.getNextStudentId();
        Student student = new Student(id, firstName, lastName, batch, true);
        addStudent(student);
    }

    public boolean removeStudent(int id) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == updatedStudent.getId()) {
                students.set(i, updatedStudent);
                return true;
            }
        }
        return false;
    }

    public List<Student> listStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudentById(int id) throws EntityNotFoundException {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student with ID " + id + " not found.");
    }

    public boolean deactivateStudent(int id) throws EntityNotFoundException {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setActive(false);
                return true;
            }
        }
        throw new EntityNotFoundException("Student with ID " + id + " not found.");
    }
}
