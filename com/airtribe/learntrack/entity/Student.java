package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.IdGenerator;

public class Student extends Person {
    private String batch;
    private boolean active;

    public Student() {
        super();
    }

    public Student(int id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    public Student(int id, String firstName, String lastName, String batch, boolean active) {
        super(id, firstName, lastName, null);
        this.batch = batch;
        this.active = active;
    }

    public Student(String firstName, String lastName, String email, String batch, boolean active) {
        super(IdGenerator.getNextStudentId(), firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    public Student(String firstName, String lastName, String batch, boolean active) {
        super(IdGenerator.getNextStudentId(), firstName, lastName, null);
        this.batch = batch;
        this.active = active;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static int getNextStudentId() {
        return IdGenerator.getNextStudentId();
    }

    public void setId(int id) {
        this.id = id;
        IdGenerator.updateStudentIdCounter(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", batch='" + batch + '\'' +
                ", active=" + active +
                '}';
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " (Student)";
    }
}
