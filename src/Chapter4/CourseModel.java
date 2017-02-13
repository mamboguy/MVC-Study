package Chapter4;

import java.util.ArrayList;

/**
 * This project requires you to explore the Java GUI framework on your own,
 * determine the appropriate classes to use, and write two Java classes that
 * create a GUI program. The first class CourseProcessor is the GUI interface,
 * and the second class Course stores information about a single course.
 * <p>
 * The program accepts and stores information about courses offered in six
 * departments: Computer Science, Mathematics, Chemistry, Physics, Botany, and
 * Zoology.
 * <p>
 * The user can do the following.
 * a. Enter information about a course by selecting a department name from a
 * combo box, typing in the course number, name, number of credits and then
 * pressing the enter button. The interface checks that the entries are
 * non-empty (display error message otherwise) and then creates a Course object
 * using the information and then stores the object in a java.util.Vector
 * object.
 * <p>
 * b. Ask to list all courses by clicking on a button labeled display (all).
 * All the objects in the Vector object are displayed. There is a scrollbar that
 * allows viewing records that cannot be displayed in the given space. Also,
 * note the department codes such as CS and MATH inserted by the program.
 * <p>
 * c. Ask to list courses of a given department by clicking on a button labeled
 * display (dept.). Courses for the selected department (via the combo box) in
 * the Vector object are displayed.
 * <p>
 * d. Quit instantly by clicking on the window's 'close' button, or close (after
 * a confirm dialog) via an 'exit' button within the frame.
 * <p>
 * Department codes Store the codes associated with departments in static arrays
 * in the class Course. This mapping should not be duplicated and should be used
 * consistently and reliably within your code. The codes are given below.
 */
//http://www3.ntu.edu.sg/home/ehchua/programming/java/J4a_GUI.html
//<editor-fold desc="Imports">
//Ctrl+Shift+I
//</editor-fold>
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

        public Course(String department, String name, int number, int credits) {
            courseDept = department;
            courseName = name;
            courseNumber = number;
            numCredits = credits;
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

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public void setCourseNumber(int courseNumber) {
            this.courseNumber = courseNumber;
        }

        public void setNumCredits(int numCredits) {
            this.numCredits = numCredits;
        }

        public void setCourseDept(String courseDept) {
            this.courseDept = courseDept;
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

        Object[][] temp = new Object[courseListing.size()][4];
        int i = 0;

        for (Course courseListing1 : courseListing) {
            temp[i][0] = courseListing1.getCourseDept();
            temp[i][1] = courseListing1.getCourseName();
            temp[i][2] = courseListing1.getCourseNumber();
            temp[i][3] = courseListing1.getNumCredits();

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
