package iuh.fit.se;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

    public class CourseList {
        private List<Course> courses;

        public CourseList() {
            courses = new ArrayList<>();
        }

        // Phương thức thêm một khóa học vào danh sách
        public void addCourse(Course course) {
            for (Course c : courses) {
                if (c.getId().equalsIgnoreCase(course.getId())) {
                    System.out.println("Error: Course ID already exists.");
                    return;
                }
            }
            courses.add(course);
        }

        // Phương thức lấy danh sách các khóa học
        public List<Course> getCourses() {
            return courses;
        }

        // Phương thức xóa một khóa học khỏi danh sách
        public void removeCourse(String id) {
            boolean removed = courses.removeIf(course -> course.getId().equalsIgnoreCase(id));
            if (!removed) {
                System.out.println("Error: Course ID not found.");
            }
        }

        // Phương thức tìm kiếm một khóa học theo mã khóa học
        public Course findCourseById(String id) {
            for (Course course : courses) {
                if (course.getId().equalsIgnoreCase(id)) {
                    return course;
                }
            }
            return null;
        }

        // Phương thức tìm kiếm các khóa học theo tên khóa học
        public List<Course> findCoursesByTitle(String title) {
            List<Course> foundCourses = courses.stream()
                    .filter(course -> course.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .collect(Collectors.toList());

            return foundCourses.isEmpty() ? null : foundCourses;
        }

        // Phương thức tìm kiếm các khóa học theo khoa phụ trách
        public List<Course> findCoursesByDepartment(String department) {
            List<Course> foundCourses = courses.stream()
                    .filter(course -> course.getDepartment().equalsIgnoreCase(department))
                    .collect(Collectors.toList());

            return foundCourses.isEmpty() ? null : foundCourses;
        }

        // Phương thức sắp xếp các khóa học theo tên khóa học
        public List<Course> sortCoursesByTitle() {
            return courses.stream()
                    .sorted((c1, c2) -> c1.getTitle().compareToIgnoreCase(c2.getTitle()))
                    .collect(Collectors.toList());
        }

        // Phương thức tìm các khóa học có số tín chỉ lớn nhất
        public List<Course> findCoursesWithMaxCredit() {
            int maxCredit = courses.stream().mapToInt(Course::getCredit).max().orElse(0);
            return courses.stream()
                    .filter(course -> course.getCredit() == maxCredit)
                    .collect(Collectors.toList());
        }

        // Phương thức tìm khoa phụ trách có nhiều khóa học nhất
        public String findDepartmentWithMostCourses() {
            return courses.stream()
                    .collect(Collectors.groupingBy(Course::getDepartment, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .max((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
                    .map(e -> e.getKey())
                    .orElse(null);
        }
    }

