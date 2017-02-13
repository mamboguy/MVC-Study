package Chapter4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CourseController
        implements ActionListener {

    private CourseModel model;
    private CourseView view;
    private String filter = "All";

    public CourseController() {
        this(new CourseModel());
    }

    public CourseController(CourseModel initial) {
        model = initial;
        view = new CourseView(getTableInformation("All"));
        view.registerListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        try {

            JButton temp = (JButton) e.getSource();
            String buttonName = temp.getText();

            switch (buttonName) {
                case "Submit":

                    String courseDept = view.getInput_courseBox();
                    String courseName = view.getInput_courseName();
                    int courseNum = view.getInput_courseNumber();
                    int courseCred = view.getInput_courseCredits();

                    model.add(courseDept, courseName, courseNum, courseCred);
                    view.clearSubmit();
                    view.insertIntoTable(model.findCourse(courseDept, courseName, courseNum, courseCred));
                    System.out.println("Course Added");
                    break;
                case "View All":
                    filter = "All";
                    break;
                case "Search":
                    filter = CourseModel.COURSE_LISTING[view.getDisplay_courseSelector()][0];
                    break;
            }

            view.filterRows(filter);
//
//        } catch (Exception ex) {
//            System.out.println("Exception thrown and caught: " + ex);
//        }
    }

    public Object[][] getTableInformation(String department) {
        return model.getTableOutputArray(department);
    }

    public static void main(String[] args) {

        CourseModel tempModel = new CourseModel();
        tempModel.add("Computer Science", "Intro to Java", 101, 3);
        tempModel.add("Physics", "Intro to Physics", 102, 3);
        tempModel.add("Chemistry", "Covalent Bonds", 311, 3);
        tempModel.add("Mathematics", "Geometry", 251, 3);
        tempModel.add("Botany", "Herbology", 301, 3);
        tempModel.add("Zoology", "Mammals", 111, 3);
        tempModel.add("Computer Science", "Intermediate Java, pt 1", 201, 3);
        tempModel.add("Computer Science", "Intermediate Java, pt 2", 202, 3);

        CourseController controller = new CourseController(tempModel);
    }

}
