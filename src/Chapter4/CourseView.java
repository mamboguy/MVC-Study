/**
 * CHANGELOG
 *
 * Added getFocusedRow and getDataAtRow(row)
 *  - getDataAtRow returns an Object[] at the passed int's corresponding row
 * 
 * Changed panel layouts to allow addition of Delete button
 */
/**
 * CourseView implements the view component of the course program. It handles
 * all aspects of GUI creation and management. It relies on the controller for
 * direction in how data is handled. It allows the controller to map buttons and
 * request data from all input fields
 * <p>
 *
 * @author Michael C
 * @date
 */
package Chapter4;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CourseView
        extends JFrame {

    private JPanel masterPanel;
    private JPanel masterPanel_topRow;
    private JButton exit;

    //<editor-fold desc="Input Area">
    //Input area panel
    private JPanel inputPanel;
    private JPanel input_labelPanel;
    private JPanel input_controlPanel;
    private JPanel input_fieldPanel;
    private JPanel input_buttonPanel;

    //Input area controls
    private JTextField input_courseName;
    private JTextField input_courseNumber;
    private JTextField input_courseCredits;
    private JComboBox input_courseDepartment;
    private JButton input_submitButton;
    private JButton input_deleteButton;
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
    private JScrollPane tablePanel;

    //Table display controls
    private JTable courseViewTable;
    private DefaultTableModel myModel;
    private final String[] columnNames = {"Abbreviation", "Course Deptartment", "Course Name", "Course Number", "Credit Hours"};
    private TableRowSorter mySorter;
    //</editor-fold>

    //<editor-fold desc="Constants">
    public static final String NO_FILTER = "";
    private static final int DEPARTMENT_COLUMN = 1;
    private static final int UNBOUND_FIELD = -1;
    private static final String FRAME_TITLE = "Chp 4 Solution";
    //</editor-fold>

    public CourseView() {
        this(null);
    }

    public CourseView(Object[][] modelInfo) {
        //Initialize components

        //<editor-fold desc="Input panel creation">		
        //Initialize inputPanel controls
        input_courseDepartment = initializeJComboBox(CourseModel.COURSE_LISTING[CourseModel.COURSE_NAMES], "Assigns course to selected department");
        input_courseCredits = initializeJTextField(5, "Defines the number of credit hours course is worth");
        input_courseName = initializeJTextField(UNBOUND_FIELD, "Name of the new course");
        input_courseNumber = initializeJTextField(5, "The course's curiculum number, i.e. CS###");
        input_submitButton = initializeJButton("Submit", "Submit", "Add course to curriculum");
        input_deleteButton = initializeJButton("Delete", "Delete", "Deletes the selected course in the table");

        //Define each panel's layout
        input_labelPanel = new JPanel();
        input_controlPanel = new JPanel();
        inputPanel = new JPanel();
        input_labelPanel.setLayout(new BoxLayout(input_labelPanel, BoxLayout.Y_AXIS));
        input_controlPanel.setLayout(new BoxLayout(input_controlPanel, BoxLayout.Y_AXIS));
        input_fieldPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        input_labelPanel.setAlignmentX(RIGHT_ALIGNMENT);
        input_buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //Yo dawg, I heard you liked panels

        //Input area user input components
        input_controlPanel.add(input_courseDepartment);
        input_controlPanel.add(input_courseName);
        input_controlPanel.add(input_courseNumber);
        input_controlPanel.add(input_courseCredits);

        //Input area labels
        String[] labels = {"Owning Department", "Course Name", "Course Number", "Credit Hours"};
        input_labelPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        for (int i = 0; i < labels.length; i++) {
            input_labelPanel.add(createLabel(labels[i]));
            input_labelPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        //Input button panel
        input_buttonPanel.add(input_deleteButton);
        input_buttonPanel.add(input_submitButton);

        //Add components to the panels
        input_fieldPanel.add(input_labelPanel);
        input_fieldPanel.add(input_controlPanel);

        inputPanel.add(input_fieldPanel);
        inputPanel.add(input_buttonPanel);
        //</editor-fold>

        //<editor-fold desc="Display control panel creation">		
        //Initialize coursePanel controls
        display_courseSelector = initializeJComboBox(CourseModel.COURSE_LISTING[CourseModel.COURSE_NAMES], "Only view courses in selected department");
        display_allButton = initializeJButton("All", "View All", "View all courses");
        display_viewButton = initializeJButton("Search", "Search", "View all courses based off department selection");

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
//Initialize JTable components
        myModel = new DefaultTableModel(modelInfo, columnNames) {

            //Force table uneditable
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }

            //Define the table's column model
            Class[] types = new Class[]{
                String.class, String.class, String.class, int.class, int.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public String getColumnName(int columnIndex) {
                return columnNames[columnIndex];
            }

        };
        courseViewTable = new JTable(myModel);
        courseViewTable.setAutoCreateRowSorter(true);

        //Creates a TableRowSorter to allow comboBox filtering
        mySorter = new TableRowSorter(myModel);
        courseViewTable.setRowSorter(mySorter);
        setNewFilter(NO_FILTER);

        //Add components to panels
        tablePanel = new JScrollPane();
        tablePanel.setViewportView(courseViewTable);
        //</editor-fold>
//
        //<editor-fold desc="Master panel Creation">
        masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel_topRow = new JPanel(new FlowLayout(FlowLayout.CENTER));

        masterPanel_topRow.add(inputPanel);
        masterPanel_topRow.add(coursePanel);

        //Exit button panel creation
        JPanel temp = new JPanel(new BorderLayout());
        exit = initializeJButton("Exit", "Exit", "Close the application after confirmation");
        temp.add(exit, BorderLayout.LINE_END);

        masterPanel.add(masterPanel_topRow);
        masterPanel.add(tablePanel);
        masterPanel.add(temp);
        //</editor-fold>

        //<editor-fold desc="Frame controls">
        this.setTitle(FRAME_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(masterPanel);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        //</editor-fold>
    }

    /**
     * Creates a JTextField of the passed size, gives it a tooltip and returns
     * it for use
     *
     * @param size    size of JTextField
     * @param toolTip tooltip of the field
     *
     * @return initialized JTextField
     */
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

    /**
     * Creates a JComboBox from passed in parameters
     *
     * @param valueArray the String array of comboBox values
     * @param toolTip    tooltip of the field
     *
     * @return initialized JComboBox
     */
    private JComboBox initializeJComboBox(String[] valueArray, String toolTip) {
        JComboBox temp = new JComboBox(valueArray);
        temp.setToolTipText(toolTip);

        return temp;
    }

    /**
     * Creates a JButton from passed parameters
     *
     * @param buttonText button's display text
     * @param toolTip    tooltip of button
     *
     * @return initialized JButton
     */
    private JButton initializeJButton(String buttonName, String buttonText, String toolTip) {
        JButton temp = new JButton(buttonText);
        temp.setToolTipText(toolTip);
        temp.setName(buttonName);

        return temp;
    }

    /**
     * Creats a JLabel of the passed String
     * <p>
     */
    private JLabel createLabel(String label) {
        return (new JLabel(label + ":"));
    }

    //<editor-fold desc="Getters for all JTextFields and JComboBoxes">
    public String getInput_courseName() {
        return input_courseName.getText();
    }

    public int getInput_courseNumber() {
        return Integer.parseInt(input_courseNumber.getText());
    }

    public int getInput_courseCredits() {
        return Integer.parseInt(input_courseCredits.getText());
    }

    public String getInput_courseBox() {
        return (String) input_courseDepartment.getSelectedItem();
    }

    public int getDisplay_courseSelector() {
        return display_courseSelector.getSelectedIndex();
    }
    //</editor-fold>

    /**
     * Clears all user input values in the submit Section
     */
    public void clearSubmitArea() {
        input_courseName.setText("");
        input_courseDepartment.setSelectedIndex(0);
        input_courseCredits.setText("");
        input_courseNumber.setText("");
    }

    /**
     * Hook for the controller to attach itself as an ActionListener to all of
     * the view's buttons
     *
     * @param al actionListener to attach
     */
    public void registerListener(ActionListener al) {
        display_viewButton.addActionListener(al);
        display_allButton.addActionListener(al);
        input_submitButton.addActionListener(al);
        input_deleteButton.addActionListener(al);
        exit.addActionListener(al);
    }

    public void insertIntoTable(Object[] newCourse) {
        myModel.addRow(newCourse);
    }

    public Object[] removeFromTable(int row) {

        Object[] temp = getDataAtRow(row);

        myModel.removeRow(courseViewTable.convertRowIndexToModel(row));

        return temp;
    }

    public void setNewFilter(String filter) {
        mySorter.setRowFilter(RowFilter.regexFilter(filter, DEPARTMENT_COLUMN));
    }

    private Object[] getDataAtRow(int row) {
        int t = courseViewTable.getSelectedRow();

        Object[] temp = new Object[courseViewTable.getColumnCount()];

        for (int i = 0; i < courseViewTable.getColumnCount(); i++) {
            temp[i] = courseViewTable.getValueAt(row, courseViewTable.convertColumnIndexToView(i));
        }

        return temp;
    }

    public int getFocusedRow() {
        return courseViewTable.getSelectedRow();
    }
}
