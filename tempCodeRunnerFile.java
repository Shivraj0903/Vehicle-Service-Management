import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class StudentManagementSystem {
    private JFrame frame;
    private JTextField nameField, ageField, gradeField;
    private JTextArea displayArea;
    private ArrayList<Student> studentList;

    public StudentManagementSystem() {
        studentList = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        
        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Labels and fields
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(20);

        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField(20);

        // Add button
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new AddStudentAction());

        // Delete button
        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(new DeleteStudentAction());

        // Display area
        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Adding components to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(gradeLabel);
        panel.add(gradeField);
        panel.add(addButton);
        panel.add(deleteButton);

        // Adding to the frame
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);

        frame.setVisible(true);
    }

    // Action Listener for adding a student
    class AddStudentAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String grade = gradeField.getText();
            studentList.add(new Student(name, age, grade));
            displayStudents();
            clearFields();
        }
    }

    // Action Listener for deleting a student
    class DeleteStudentAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            studentList.removeIf(student -> student.getName().equalsIgnoreCase(name));
            displayStudents();
            clearFields();
        }
    }

    // Display all students in the text area
    private void displayStudents() {
        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            sb.append(student).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    // Clear input fields
    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        gradeField.setText("");
    }

    public static void main(String[] args) {
        new StudentManagementSystem();
    }
}
class Student {
    private String name;
    private int age;
    private String grade;

    public Student(String name, int age, String grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Grade: " + grade;
    }
}
