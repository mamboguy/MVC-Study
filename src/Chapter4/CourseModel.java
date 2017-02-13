package Chapter4;

import java.util.ArrayList;

/**
 * Check inputs before accepting submit
 * Edit table entries
 * Delete table entries
 * <p>
 * Department codes Store the codes associated with departments in static arrays
 * in the class Course. This mapping should not be duplicated and should be used
 * consistently and reliably within your code. The codes are given below.
 */
//http://www3.ntu.edu.sg/home/ehchua/programming/java/J4a_GUI.html

public class CourseModel {

    ArrayList<Course> courseListing;

    public static final String[][] COURSE_LISTING = {
        {"Computer Science", "Physics", "Chemistry", "Mathematics", "Botany", "Zoology"},
        {"CS", "PHY", "CHEM", "MATH", "BOT", "ZOO"}};

    public CourseModel() {
        courseListing = new ArrayList();
    }

    private class Course {

        private String courseName;
        private int courseNumber;
        private int numCredits;
        private String courseDept;
        private String courseShort;

        public Course(String department, String name, int number, int credits) {
            courseDept = department;
            courseName = name;
            courseNumber = number;
            numCredits = credits;

            courseShort = COURSE_LISTING[1][getCourseIndex(department)];
            courseShort += courseNumber;
        }

        public String getCourseName() {
            return courseName;
        }

        public int getCourseNumber() {
            return courseNumber;
        }

        public int getNumCredits() {
            return numCredits;
        }

        public String getCourseDept() {
            return courseDept;
        }

        public String getShortHand() {
            return courseShort;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public void setCourseNumber(int courseNumber) {
            this.courseNumber = courseNumber;
            this.courseShort = COURSE_LISTING[1][getCourseIndex(courseDept)] + courseNumber;
        }

        public void setNumCredits(int numCredits) {
            this.numCredits = numCredits;
        }

        public void setCourseDept(String courseDept) {
            this.courseDept = courseDept;
            this.courseShort = COURSE_LISTING[1][getCourseIndex(courseDept)] + courseNumber;
        }

        private int getCourseIndex(String department) {
            for (int i = 0; i < COURSE_LISTING[0].length; i++) {
                if (department.equals(COURSE_LISTING[0][i])) {
                    return i;
                }
            }
            return -1;
        }

        public boolean equals(Course b) {
            return (courseNumber == b.getCourseNumber()
                    && courseName.equals(b.getCourseName())
                    && courseDept.equals(b.getCourseDept())
                    && numCredits == b.getNumCredits());
        }

        public String toString() {
            return "{" + courseName + ", " + courseDept + ", " + courseNumber + ", " + numCredits + "}";
        }
    }

    public void add(String department, String name, int number, int credits) {
        courseListing.add(new Course(department, name, number, credits));
    }

    public Object[] findCourse(String department, String name, int number, int credits) {
        Object[] temp;

        for (Course courseList : courseListing) {
            if (courseList.getCourseDept().equals(department)
                && courseList.getCourseName().equals(name)
                && courseList.getCourseNumber() == number
                && courseList.getNumCredits() == credits) {

                temp = new Object[]{
                    courseList.getShortHand(),
                    courseList.getCourseDept(),
                    courseList.getCourseName(),
                    courseList.getCourseNumber(),
                    courseList.getNumCredits()};

                return temp;
            }
        }

        return null;
    }

    public Object[][] getTableOutputArray() {

        Object[][] temp = new Object[courseListing.size()][5];
        int i = 0;

        for (Course courseListing1 : courseListing) {
            temp[i][0] = courseListing1.getShortHand();
            temp[i][1] = courseListing1.getCourseDept();
            temp[i][2] = courseListing1.getCourseName();
            temp[i][3] = courseListing1.getCourseNumber();
            temp[i][4] = courseListing1.getNumCredits();

            i++;
        }

        return temp;
    }

    @Override
    public String toString() {
        String temp = "";

        for (Course courseListing1 : courseListing) {
            temp += courseListing1.toString() + ", \n";
        }

        return temp.substring(0, temp.length() - 3);
    }
}
