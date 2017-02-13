package Chapter4;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
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
    private JScrollPane tablePanel;

    //Table display controls
    private JTable courseViewTable;
    private Object[][] tableInformation;
    private DefaultTableModel myModel;
    private final String[] columnNames = {"Abbreviation","Course Deptartment", "Course Name", "Course Number", "Credit Hours"};
    private TableRowSorter mySorter;
    RowFilter<Object, Object> myFilter;
    private String filter = "";
    private JButton exit;
    //</editor-fold>

    public CourseView() {
        this(null);
    }

    public CourseView(Object[][] tableInfo) {
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

        String[] labels = {"Owning Department", "Course Name", "Course Number", "Credit Hours"};

        input_labelPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        for (int i = 0; i < labels.length; i++) {
            input_labelPanel.add(createLabel(labels[i]));
            input_labelPanel.add(Box.createRigidArea(new Dimension(0, 5)));
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
        myModel = new DefaultTableModel(tableInformation, columnNames) {

            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }

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

        mySorter = new TableRowSorter(myModel);

        courseViewTable.setRowSorter(mySorter);
        setFilter(filter);

        initializeTableInformation(tableInfo);

        //Add components to panels
        tablePanel = new JScrollPane();
        tablePanel.setViewportView(courseViewTable);

        //</editor-fold>
//
        exit = initializeJButton("Exit", "Close the application after confirmation");
//
        //<editor-fold desc="Master panel Creation">
        masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel_topRow = new JPanel(new FlowLayout(FlowLayout.CENTER));

        masterPanel_topRow.add(inputPanel);
        masterPanel_topRow.add(coursePanel);

        masterPanel.add(masterPanel_topRow);
        masterPanel.add(tablePanel);

        JPanel temp = new JPanel(new BorderLayout());
        temp.add(exit, BorderLayout.LINE_END);
        masterPanel.add(temp);
        //</editor-fold>

        //<editor-fold desc="Frame controls">
        this.setTitle("Course Chp 4 Solution");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(masterPanel);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        //</editor-fold>

        Dimension d = exit.getSize();
        exit.setMaximumSize(d);
        exit.setPreferredSize(d);
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
        return temp;
    }

    private JLabel createLabel(String label) {
        return (new JLabel(label + ":"));
    }

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
        return (String) input_courseBox.getSelectedItem();
    }

    public int getDisplay_courseSelector() {
        return display_courseSelector.getSelectedIndex();
    }

    public void clearSubmit() {
        input_courseName.setText("");
        input_courseBox.setSelectedIndex(0);
        input_courseCredits.setText("");
        input_courseNumber.setText("");
    }

    public void registerListener(ActionListener al) {
        display_viewButton.addActionListener(al);
        display_allButton.addActionListener(al);
        input_submitButton.addActionListener(al);
        exit.addActionListener(al);
    }

    public void insertIntoTable(Object[] newCourse) {
        myModel.addRow(newCourse);
    }

    public void removeFromTable(int row) {
        myModel.removeRow(row);
    }

    public void setFilter(String filter) {
        this.filter = filter;

        myFilter = RowFilter.regexFilter(this.filter, 1);

        mySorter.setRowFilter(myFilter);
    }

    public void initializeTableInformation(Object[][] tableInfo) {
        tableInformation = tableInfo;

        if (tableInformation != null) {
            for (int i = 0; i < tableInfo.length; i++) {
                myModel.addRow(tableInformation[i]);
            }
        }
    }
}
