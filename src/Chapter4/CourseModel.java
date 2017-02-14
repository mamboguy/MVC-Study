/** CHANGELOG.
 * CHANGELOG:
 * - Added more constants and replaced some values with new constants
 * - Documented code
 * - Reorganized a few methods and added new fold regions
 */
/**
 * CourseModel implements the model component of the course program. It handles
 * all functions with regards to accessing and storing all course data used by
 * the program.
 * <p>
 * Used resources:
 * <p>
 * StackOverflow
 * <p>
 * http://www3.ntu.edu.sg/home/ehchua/programming/java/J4a_GUI.html
 * <p>
 * S. Scott
 */
package Chapter4;

import java.util.ArrayList;

public class CourseModel {

    ArrayList<Course> courseListing;

    //<editor-fold desc="Constants">
    public static final String[][] COURSE_LISTING = {
        {"Computer Science", "Physics", "Chemistry", "Mathematics", "Botany", "Zoology"},
        {"CS", "PHY", "CHEM", "MATH", "BOT", "ZOO"}};
    public static final int COURSE_NAMES = 0;
    public static final int COURSE_CODES = 1;
    //</editor-fold>

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

            courseShort = COURSE_LISTING[COURSE_CODES][getCourseIndex(department)];
            courseShort += courseNumber;
        }

        //<editor-fold desc="Getters">
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
        //</editor-fold>

        //<editor-fold desc="Setters">
        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        //Updates the courseShort variable as well
        public void setCourseNumber(int courseNumber) {
            this.courseNumber = courseNumber;
            this.courseShort = COURSE_LISTING[COURSE_CODES][getCourseIndex(courseDept)] + courseNumber;
        }

        public void setNumCredits(int numCredits) {
            this.numCredits = numCredits;
        }

        //Updates the courseShort variable as well
        public void setCourseDept(String courseDept) {
            this.courseDept = courseDept;
            this.courseShort = COURSE_LISTING[COURSE_CODES][getCourseIndex(courseDept)] + courseNumber;
        }
        //</editor-fold>

        /**
         * Gets the index in the COURSE_LISTING constant the passed department
         *
         * @param department department to find in COURSE_LISTING
         *
         * @return index of department in COURSE_LISTING
         */
        private int getCourseIndex(String department) {
            for (int i = 0; i < COURSE_LISTING[COURSE_NAMES].length; i++) {
                if (department.equals(COURSE_LISTING[COURSE_NAMES][i])) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * Compares two courses to check if equal
         *
         * @param b Course to compare
         *
         * @return <code>true</code> if equal, <code>false </code> if not
         */
        public boolean equals(Course b) {
            return (courseNumber == b.getCourseNumber()
                    && courseName.equals(b.getCourseName())
                    && courseDept.equals(b.getCourseDept())
                    && numCredits == b.getNumCredits());
        }

        @Override
        public String toString() {
            return "{" + courseName + ", " + courseDept + ", " + courseNumber + ", " + numCredits + "}";
        }
    }

    public void addNewCourse(String department, String name, int number, int credits) {
        courseListing.add(new Course(department, name, number, credits));
    }

    /**
     * Returns a course in the form of an Object[] to allow insertion into
     * view's table. Should only be utilized by the controller
     *
     * @param department
     * @param name
     * @param number
     * @param credits
     *
     * @return
     */
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

    /**
     * Used to initialize the view's table. Should only be called by the
     * controller.
     *
     * @return Object[][] array for creating the table in the view
     */
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
