package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addCourse(String courseName, String description, int durationInWeeks) {
        int id = Course.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks, true);
        addCourse(course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public boolean setCourseActive(int id, boolean active) throws EntityNotFoundException {
        for (Course course : courses) {
            if (course.getId() == id) {
                course.setActive(active);
                return true;
            }
        }
        throw new EntityNotFoundException("Course with ID " + id + " not found.");
    }
}
