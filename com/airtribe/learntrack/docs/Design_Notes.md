# Design Notes

## Why use ArrayList instead of array?
- **ArrayList** provides dynamic resizing, so you don't need to know the number of students, courses, or enrollments in advance. It also offers convenient methods for adding, removing, and searching elements, making the code simpler and more maintainable than using fixed-size arrays.

## Where are static members used and why?
- **Static counters** (e.g., `studentIdCounter`, `courseIdCounter`) are used in the `Student` and `Course` classes to auto-generate unique IDs for new objects. This ensures each entity gets a unique identifier without external tracking.
- **Static methods** like `getNextStudentId()` and `getNextCourseId()` are used to encapsulate the logic for generating the next available ID.

## Where is inheritance used and what is gained from it?
- The `Person` class is a base class for `Student` and `Trainer`. This allows common fields (`id`, `firstName`, `lastName`, `email`) and methods (like `getDisplayName()`) to be defined once in `Person` and reused in subclasses. Inheritance reduces code duplication, improves maintainability, and makes the codebase easier to extend (e.g., adding new types of people in the future).

