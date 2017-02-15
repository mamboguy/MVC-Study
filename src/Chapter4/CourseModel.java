/** CHANGELOG.
 *
 * Changed a course's credit hours to double
 * Modified addCourse and findCourse to accept Object[] as a parameter
 */
/**
 * CourseModel implements the model component of the course program. It handles
 * all functions with regards to accessing and storing all course data used by
 * the program.
 *
 * Used resources:
 * - StackOverflow
 * - http://www3.ntu.edu.sg/home/ehchua/programming/java/J4a_GUI.html
 * - S. Scott
 */
package Chapter4;

import java.util.ArrayList;

public class CourseModel {

    ArrayList<Course> courseListing;

    //<editor-fold desc="Constants">
    public static final String[][] COURSE_LISTING = {
        {"Computer Science", "Physics", "Chemistry", "Mathematics", "Botany", "Zoology"},
        {"CS", "PHY", "CHEM", "MATH", "BOT", "ZOO"}};
    public static final int COURSE_NAMES_ARRAY = 0;
    public static final int COURSE_CODES_ARRAY = 1;

    public static final int ABBREVIATION = 0;
    public static final int DEPARTMENT = 1;
    public static final int COURSE_NAME = 2;
    public static final int COURSE_NUMBER = 3;
    public static final int CREDIT_HOURS = 4;
    //</editor-fold>

    public CourseModel() {
        courseListing = new ArrayList();
    }

    private class Course {

        private String courseName;
        private int courseNumber;
        private double numCredits;
        private String courseDept;
        private String courseShort;

        public Course(String department, String name, int number, double credits) {
            courseDept = department;
            courseName = name;
            courseNumber = number;
            numCredits = credits;

            courseShort = COURSE_LISTING[COURSE_CODES_ARRAY][getCourseIndex(department)];
            courseShort += courseNumber;
        }

        //<editor-fold desc="Getters">
        public String getCourseName() {
            return courseName;
        }

        public int getCourseNumber() {
            return courseNumber;
        }

        public double getNumCredits() {
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
            this.courseShort = COURSE_LISTING[COURSE_CODES_ARRAY][getCourseIndex(courseDept)] + courseNumber;
        }

        public void setNumCredits(double numCredits) {
            this.numCredits = numCredits;
        }

        //Updates the courseShort variable as well
        public void setCourseDept(String courseDept) {
            this.courseDept = courseDept;
            this.courseShort = COURSE_LISTING[COURSE_CODES_ARRAY][getCourseIndex(courseDept)] + courseNumber;
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
            for (int i = 0; i < COURSE_LISTING[COURSE_NAMES_ARRAY].length; i++) {
                if (department.equals(COURSE_LISTING[COURSE_NAMES_ARRAY][i])) {
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
        @Override
        public boolean equals(Object b) {
            Course temp;
            try {
                temp = (Course) b;
            } catch (Exception e) {
                return false;
            }

            return (courseNumber == temp.getCourseNumber()
                    && courseName.equals(temp.getCourseName())
                    && courseDept.equals(temp.getCourseDept())
                    && numCredits == temp.getNumCredits());
        }

        @Override
        public String toString() {
            return "{" + courseName + ", " + courseDept + ", " + courseNumber + ", " + numCredits + "}";
        }
    }

    private Course convertObjectToCourse(Object[] course) {
        Course temp;
        try {
            temp = new Course((String) course[DEPARTMENT],
                              (String) course[COURSE_NAME],
                              (int) course[COURSE_NUMBER],
                              (double) course[CREDIT_HOURS]);

        } catch (Exception e) {
            return null;
        }

        return temp;
    }

    public boolean deleteCourse(Object[] course) {
        return courseListing.remove(convertObjectToCourse(course));
//        courseListing.remove(getCourseIndex(course));
    }

//    private int getCourseIndex(Object[] course) {
//        Course temp = new Course((String) course[1], (String) course[2], (int) course[3], (int) course[4]);
//
//        for (int i = 0; i < courseListing.size(); i++) {
//            if (courseListing.get(i).equals(temp)) {
//                return i;
//            }
//        }
//        return -1;
//    }
    public boolean addNewCourse(Object[] course) {
        return courseListing.add(convertObjectToCourse(course));
    }

    /**
     * Returns a course in the form of an Object[] to allow insertion into
     * view's table. Should only be utilized by the controller
     *
     * @return
     */
    public Object[] findCourse(Object[] course) {
        Course tempCourse = convertObjectToCourse(course);
        Object[] temp;

        for (Course courseList : courseListing) {
            if (courseList.equals(tempCourse)) {

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

            temp[i][ABBREVIATION] = courseListing1.getShortHand();
            temp[i][DEPARTMENT] = courseListing1.getCourseDept();
            temp[i][COURSE_NAME] = courseListing1.getCourseName();
            temp[i][COURSE_NUMBER] = courseListing1.getCourseNumber();
            temp[i][CREDIT_HOURS] = courseListing1.getNumCredits();

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
