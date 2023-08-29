import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// 學生類
class Student {
    private String name;
    private double score;

    // 學生類的建構子
    public Student(String name, double score) {
        this.name = name;
        this.score = score;
    }

    // 獲取學生姓名
    public String getName() {
        return name;
    }

    // 獲取學生成績
    public double getScore() {
        return score;
    }

    // 重寫 toString 方法，用於方便輸出學生信息
    @Override
    public String toString() {
        return "Name: " + name + ", Score: " + score;
    }
}

// 學生成績管理系統類
class StudentManagementSystemGUI extends JFrame {
    private ArrayList<Student> students;
    private JTextArea outputTextArea;
    private JTextField nameField;
    private JTextField scoreField;

    public StudentManagementSystemGUI() {
        students = new ArrayList<>();
        outputTextArea = new JTextArea(15, 30);
        outputTextArea.setEditable(false);

        nameField = new JTextField(20);
        scoreField = new JTextField(20);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        JButton displayButton = new JButton("Display Students");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });

        JButton averageButton = new JButton("Calculate Average Score");
        averageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAverageScore();
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Student Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Student Score:"));
        inputPanel.add(scoreField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(averageButton);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputTextArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addStudent() {
        String name = nameField.getText();
        double score = Double.parseDouble(scoreField.getText());
        students.add(new Student(name, score));
        nameField.setText("");
        scoreField.setText("");
    }

    private void displayStudents() {
        outputTextArea.setText("Student List:\n");
        for (Student student : students) {
            outputTextArea.append(student.toString() + "\n");
        }
    }

    private void calculateAverageScore() {
        if (students.isEmpty()) {
            outputTextArea.setText("No students to calculate average score.");
            return;
        }

        double totalScore = 0;
        for (Student student : students) {
            totalScore += student.getScore();
        }
        double averageScore = totalScore / students.size();
        outputTextArea.setText("Average score: " + averageScore);
    }
}

public class Main {
    public static void main(String[] args) {
        // 在事件調度線程中啟動視窗應用
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagementSystemGUI();
            }
        });
    }
}
