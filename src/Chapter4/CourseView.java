package Chapter4;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
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

    //Input area controls
    private JButton input_submitButton;
    //</editor-fold>

    //<editor-fold desc="Course Search Area">
    //Course display panel
    private JPanel coursePanel;
    private JPanel course_controlPanel;

    //Course display controls
    private JButton display_allButton;
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
        //Define each panel's layout
        inputPanel = new JPanel(new GridLayout(5, 1, 5, 0));
        inputPanel.setAlignmentX(RIGHT_ALIGNMENT);
        input_submitButton = initializeJButton("Submit", "Add course to curriculum");

        //Add components to the panel
        inputPanel.add(newJComboBox("Owning Department", CourseModel.COURSE_LISTING[0], "Assigns course to selected department"));
        inputPanel.add(newJTextField("Course Name", -1, "Name of the new course"));
        inputPanel.add(newJTextField("Course Number", 5, "The course's curiculum number, i.e. CS###"));
        inputPanel.add(newJTextField("Credit Hours", 5, "Defines the number of credit hours course is worth"));
        inputPanel.add(input_submitButton);
        //</editor-fold>

        //<editor-fold desc="Display control panel creation">		
        //Initialize coursePanel controls               
        display_allButton = initializeJButton("View All", "View all courses");
        display_viewButton = initializeJButton("Search", "View all courses based off department selection");

        //Define each panel's layout
        course_controlPanel = new JPanel(new FlowLayout());

        coursePanel = new JPanel(new GridLayout(2, 1, 5, 0));

        //Add components to the panels
        coursePanel.add(newJComboBox("Select a Department", CourseModel.COURSE_LISTING[0], "Only view courses in selected department"));
        coursePanel.add(course_controlPanel);

        course_controlPanel.add(display_allButton);
        course_controlPanel.add(display_viewButton);
        //</editor-fold>

        //<editor-fold desc="Table Panel Initialization">
        //Initialize tablePanel controls
        //courseViewTable = new JTable()
        //</editor-fold>
        //<editor-fold desc="Master Panel Initialization">
        masterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        masterPanel_topRow = new JPanel(new FlowLayout(FlowLayout.CENTER));

        masterPanel_topRow.add(inputPanel);
        masterPanel_topRow.add(coursePanel);

        masterPanel.add(masterPanel_topRow);
//        masterPanel.add(tablePanel);
        //</editor-fold>

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(masterPanel);
        this.pack();
        this.setVisible(true);
    }

    private JPanel newJTextField(String label, int size, String toolTip) {
        JTextField tempTxtFld;

        if (size <= 0) {
            tempTxtFld = new JTextField();
        } else {
            tempTxtFld = new JTextField(size);
        }
        tempTxtFld.setToolTipText(toolTip);

        JLabel tempLbl = new JLabel(label + ":");

        JPanel temp = new JPanel();
        temp.add(tempLbl);
        temp.add(tempTxtFld);

        return temp;
    }

    private JPanel newJComboBox(String label, String[] valueArray, String toolTip) {
        JComboBox tempCombo = new JComboBox(valueArray);
        tempCombo.setToolTipText(toolTip);

        JLabel tempLbl = new JLabel(label + ":");

        JPanel temp = new JPanel();
        temp.add(tempLbl);
        temp.add(tempCombo);

        return temp;
    }

    private JButton initializeJButton(String buttonText, String toolTip) {
        JButton tempBtn;

        if (buttonText == null) {
            tempBtn = new JButton();
        } else {
            tempBtn = new JButton(buttonText);
        }

        tempBtn.setToolTipText(toolTip);
        tempBtn.addActionListener(this);

        return tempBtn;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        switch (e.getSource().toString()) {
            case "Submit":

                break;
            case "View All":

                break;
            case "Search":

                break;
        }
    }
}
