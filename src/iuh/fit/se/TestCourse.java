package iuh.fit.se;

import java.util.List;
import java.util.Scanner;

public class TestCourse {
    public static void main(String[] args) {
        CourseList courseList = new CourseList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add a course");
            System.out.println("2. List all courses");
            System.out.println("3. Remove a course");
            System.out.println("4. Find a course by ID");
            System.out.println("5. Find courses by title");
            System.out.println("6. Find courses by department");
            System.out.println("7. Sort courses by title");
            System.out.println("8. Find courses with max credit");
            System.out.println("9. Find department with most courses");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter course ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter course title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter course credit: ");
                    int credit = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter course department: ");
                    String department = scanner.nextLine();
                    try {
                        Course course = new Course(id, title, credit, department);
                        courseList.addCourse(course);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    List<Course> courses = courseList.getCourses();
                    if (courses.isEmpty()) {
                        System.out.println("No courses available.");
                    } else {
                        System.out.println(String.format("%-10s %-30s %-10s %-20s", "ID", "Title", "Credit", "Department"));
                        courses.forEach(System.out::println);
                    }
                    break;
                case 3:
                    System.out.print("Enter course ID to remove: ");
                    String removeId = scanner.nextLine();
                    courseList.removeCourse(removeId);
                    break;
                case 4:
                    System.out.print("Enter course ID to find: ");
                    String findId = scanner.nextLine();
                    Course foundCourse = courseList.findCourseById(findId);
                    if (foundCourse != null) {
                        System.out.println("Course found:");
                        System.out.println(foundCourse);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter course title to search: ");
                    String searchTitle = scanner.nextLine();
                    List<Course> foundCoursesByTitle = courseList.findCoursesByTitle(searchTitle);
                    if (foundCoursesByTitle != null) {
                        System.out.println("Courses found:");
                        foundCoursesByTitle.forEach(System.out::println);
                    } else {
                        System.out.println("No courses found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter department to search: ");
                    String searchDept = scanner.nextLine();
                    List<Course> foundCoursesByDept = courseList.findCoursesByDepartment(searchDept);
                    if (foundCoursesByDept != null) {
                        System.out.println("Courses found:");
                        foundCoursesByDept.forEach(System.out::println);
                    } else {
                        System.out.println("No courses found.");
                    }
                    break;
                case 7:
                    List<Course> sortedCourses = courseList.sortCoursesByTitle();
                    System.out.println("Courses sorted by title:");
                    sortedCourses.forEach(System.out::println);
                    break;
                case 8:
                    List<Course> maxCreditCourses = courseList.findCoursesWithMaxCredit();
                    System.out.println("Courses with maximum credit:");
                    maxCreditCourses.forEach(System.out::println);
                    break;
                case 9:
                    String departmentWithMostCourses = courseList.findDepartmentWithMostCourses();
                    if (departmentWithMostCourses != null) {
                        System.out.println("Department with most courses: " + departmentWithMostCourses);
                    } else {
                        System.out.println("No courses found.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

