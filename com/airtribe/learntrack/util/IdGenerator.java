package com.airtribe.learntrack.util;

public class IdGenerator {
    private static int studentIdCounter = 1;
    private static int courseIdCounter = 1;

    public static int getNextStudentId() {
        return studentIdCounter++;
    }

    public static int getNextCourseId() {
        return courseIdCounter++;
    }

    public static void updateStudentIdCounter(int id) {
        if (id >= studentIdCounter) {
            studentIdCounter = id + 1;
        }
    }

    public static void updateCourseIdCounter(int id) {
        if (id >= courseIdCounter) {
            courseIdCounter = id + 1;
        }
    }

    public static boolean isValidInt(String input) {
        if (input == null) return false;
        try {
            Integer.parseInt(input.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int parseIntOrDefault(String input, int defaultValue) {
        if (isValidInt(input)) {
            return Integer.parseInt(input.trim());
        }
        return defaultValue;
    }
}
