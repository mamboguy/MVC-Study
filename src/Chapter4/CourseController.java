/**
 * CHANGELOG:
 * - Change button's text check to button's name check
 */
/**
 * CourseController implements the controller component of the course program.
 * It handles all the logic needed for the GUI to handle component events,
 * pushes new data to the view when required and handles data pass off to the
 * model from the GUI<p>
 * -------------------------------------
 * <p>
 * TODO List
 * - Input checking on user input
 * - Allow editing of courses already in table
 * - Allow deletion of courses from table (after prompt)
 * - Restricting textfield to certain formats and values (i.e. ints only)
 * - Restrict resizing of Exit button during window resize
 * - Allow adding new courses to curriculum
 * - Change the default sizes of columns to be more appropriate
 * - Restrict each column's minimum size
 * - Change filter to allow multiple departments
 * - Make JOptionPane popup on screen in focus rather than primary monitor
 * -------------------------------------
 * What I've learned:
 * - MVC basic setup. Really simplifies code into easy to use/understand modules
 * - Basic GUI creation and ActionListener usage
 * - Value of standard libraries
 * - Basic Javadoc formatting (newline, <b>bold</b> and <code>code()</code>
 * - Git repository is a lifesaver
 * - Using constants to substitute values for code readability/prevent multiple
 * changes to code on simple model change
 * - Code folding, Ctrl+Shift+I for import management, Alt+Ins for auto-creating
 * getters/setters
 * - Template manipulation for personal ease of use

 * @author Michael C
 */
package Chapter4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class CourseController
        implements ActionListener {

    private CourseModel model;
    private CourseView view;

    /**
     * Overloaded constructor to pass in new model
     */
    public CourseController() {
        this(new CourseModel());
    }

    /**
     * Creates a view and model to use and then assigns itself as the listener
     * to the view
     *
     * @param initial pre-built CourseModel pass-in
     */
    public CourseController(CourseModel initial) {
        model = initial;
        view = new CourseView(model.getTableOutputArray());
        view.registerListener(this);
    }

    /**
     * Handles all GUI button presses
     *
     * @param buttonPressed event generated by a button pressed
     */
    @Override
    public void actionPerformed(ActionEvent buttonPressed) {
//        try {

        JButton temp = (JButton) buttonPressed.getSource();
        String buttonName = temp.getName();

        //Handles each button press appropriately
        switch (buttonName) {

            /*
             * Submit will attempt to add a new course to the model from the
             * values given by the user in the GUI
             */
            case "Submit":

                String courseDept = view.getInput_courseBox();
                String courseName = view.getInput_courseName();
                int courseNum = view.getInput_courseNumber();
                int courseCred = view.getInput_courseCredits();

                model.addNewCourse(courseDept, courseName, courseNum, courseCred);
                view.clearSubmitArea();
                view.insertIntoTable(model.findCourse(courseDept, courseName, courseNum, courseCred));
                break;

            case "All":
                view.setNewFilter(CourseView.NO_FILTER);
                break;

            case "Search":
                view.setNewFilter(CourseModel.COURSE_LISTING[CourseModel.COURSE_NAMES][view.getDisplay_courseSelector()]);
                break;

            /*
             * Generates a confirmation box to ensure the user wishes to exit
             */
            case "Exit":
                String options[] = {"Exit", "Cancel"};

                if (JOptionPane.showOptionDialog(null,
                                                 "Are you sure you want to exit?",
                                                 "Exit confirmation",
                                                 JOptionPane.YES_NO_OPTION,
                                                 JOptionPane.QUESTION_MESSAGE,
                                                 null,
                                                 options,
                                                 options[1]) == JOptionPane.YES_OPTION) {

                    System.exit(0);
                }

                break;
        }
    }
//
//        } catch (Exception ex) {
//            System.out.println("Exception thrown and caught: " + ex);
//        }
//  }

    /*
     * Creates a model for importing to allow easy testing of program
     * functionality
     */
    public static void main(String[] args) {

        CourseModel tempModel = new CourseModel();
        tempModel.addNewCourse("Computer Science", "Intro to Java", 101, 3);
        tempModel.addNewCourse("Physics", "Intro to Physics", 102, 3);
        tempModel.addNewCourse("Chemistry", "Covalent Bonds", 311, 3);
        tempModel.addNewCourse("Mathematics", "Geometry", 251, 3);
        tempModel.addNewCourse("Botany", "Herbology", 301, 3);
        tempModel.addNewCourse("Zoology", "Mammals", 111, 3);
        tempModel.addNewCourse("Computer Science", "Intermediate Java, pt 1", 201, 3);
        tempModel.addNewCourse("Computer Science", "Intermediate Java, pt 2", 202, 3);

        CourseController controller = new CourseController(tempModel);
    }

}
