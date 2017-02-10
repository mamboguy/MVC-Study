package Chapter4;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class CourseView
        extends JFrame
        implements ActionListener {

    private JPanel masterPanel;
    private JPanel masterPanel_topRow;

    //<editor-fold desc="Input Area">
    //Input area panel
    private JPanel inputPanel;
    private JPanel input_labelPanel;
    private JPanel input_controlPanel;

    //Input area controls
    private JTextField input_courseName;
    private JTextField input_courseNumber;
    private JTextField input_courseCredits;
    private JComboBox input_courseBox;
    private JButton input_submitButton;
    //</editor-fold>

    //<editor-fold desc="Course Search Area">
    //Course display panel
    private JPanel coursePanel;
    private JPanel course_deptSelectPanel;
    private JPanel course_controlPanel;

    //Course display controls
    private JButton display_allButton;
    private JComboBox display_courseSelector;
    private JButton display_viewButton;
    //</editor-fold>

    //<editor-fold desc="Table Area">
    //Table display
    private JPanel tablePanel;

    //Table display controls
    private JTable courseViewTable;
    //</editor-fold>

    public CourseView() {
        //Initialize components

        //<editor-fold desc="Input panel creation">		
        //Initialize inputPanel controls
        input_courseBox = initializeJComboBox(CourseModel.COURSE_LISTING[0], "Assigns course to selected department");
        input_courseCredits = initializeJTextField(5, "Defines the number of credit hours course is worth");
        input_courseName = initializeJTextField(-1, "Name of the new course");
        input_courseNumber = initializeJTextField(5, "The course's curiculum number, i.e. CS###");
        input_submitButton = initializeJButton("Submit", "Add course to curriculum");

        //Define each panel's layout
        input_labelPanel = new JPanel();
        input_controlPanel = new JPanel();
        input_labelPanel.setLayout(new BoxLayout(input_labelPanel, BoxLayout.Y_AXIS));
        input_controlPanel.setLayout(new BoxLayout(input_controlPanel, BoxLayout.Y_AXIS));
        inputPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        input_labelPanel.setAlignmentX(RIGHT_ALIGNMENT);
        
        //Input area labels
        input_controlPanel.add(input_courseBox);
        input_controlPanel.add(input_courseName);
        input_controlPanel.add(input_courseNumber);
        input_controlPanel.add(input_courseCredits);
        input_controlPanel.add(input_submitButton);

        String[] labels = {"Owning Department","Course Name","Course Number","Credit Hours"};
        
        input_labelPanel.add(Box.createRigidArea(new Dimension(0,5)));
        for (int i = 0; i < labels.length; i++) {
            input_labelPanel.add(createLabel(labels[i]));
            input_labelPanel.add(Box.createRigidArea(new Dimension(0,5)));
        }

        //Add components to the panels
        inputPanel.add(input_labelPanel);
        inputPanel.add(input_controlPanel);
        //</editor-fold>

        //<editor-fold desc="Display control panel creation">		
        //Initialize coursePanel controls
        display_courseSelector = initializeJComboBox(CourseModel.COURSE_LISTING[0], "Only view courses in selected department");
        display_allButton = initializeJButton("View All", "View all courses");
        display_viewButton = initializeJButton("Search", "View all courses based off department selection");

        //Define each panel's layout
        course_controlPanel = new JPanel(new FlowLayout());
        course_deptSelectPanel = new JPanel(new FlowLayout());

        coursePanel = new JPanel(new GridLayout(2, 1, 5, 0));

        //Add components to the panels
        coursePanel.add(course_deptSelectPanel);
        coursePanel.add(course_controlPanel);

        course_deptSelectPanel.add(createLabel("Select a department"));
        course_deptSelectPanel.add(display_courseSelector);

        course_controlPanel.add(display_allButton);
        course_controlPanel.add(display_viewButton);
        //</editor-fold>

        //<editor-fold desc="Table panel creation">		
        //Initialize tablePanel controls
        //courseViewTable = new JTable()
        //</editor-fold>
        //<editor-fold desc="Master Panel Initialize">
        masterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        masterPanel_topRow = new JPanel(new FlowLayout(FlowLayout.CENTER));

        masterPanel_topRow.add(inputPanel);
        masterPanel_topRow.add(coursePanel);

        masterPanel.add(masterPanel_topRow);
        //masterPanel.add(tablePanel);
        //</editor-fold>

        this.setTitle("Course Chp 4 Solution");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(masterPanel);
        this.pack();
        this.setVisible(true);
    }

    private JTextField initializeJTextField(int size, String toolTip) {
        JTextField temp;

        if (size <= 0) {
            temp = new JTextField();
        } else {
            temp = new JTextField(size);
        }
        temp.setToolTipText(toolTip);

        return temp;
    }

    private JComboBox initializeJComboBox(String[] valueArray, String toolTip) {
        JComboBox temp = new JComboBox(valueArray);
        temp.setToolTipText(toolTip);

        return temp;
    }

    private JButton initializeJButton(String buttonText, String toolTip) {
        JButton temp;

        if (buttonText == null) {
            temp = new JButton();
        } else {
            temp = new JButton(buttonText);
        }

        temp.setToolTipText(toolTip);
        temp.addActionListener(this);

        return temp;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        JButton temp = (JButton) e.getSource();

        switch (temp.getText()) {
            case "Submit":
                System.out.println("Submit");
                break;
            case "View All":
                System.out.println("View All");
                break;
            case "Search":
                System.out.println("Search");
                break;
        }
    }

    private JLabel createLabel(String label) {
        return (new JLabel(label + ":"));
    }
}
