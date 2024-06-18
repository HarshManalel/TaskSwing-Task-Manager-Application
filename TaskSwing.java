import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Task {
    String description;
    boolean completed;

    Task(String description) {
        this.description = description;
        this.completed = false;
    }
}

class TaskManager {
    private ArrayList<Task> tasks;

    TaskManager() {
        this.tasks = new ArrayList<>();
    }

    void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("Task added: " + description);
    }

    ArrayList<Task> getTasks() {
        return tasks;
    }

    void markTaskAsComplete(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.completed = true;
            System.out.println("Task marked as complete: " + task.description);
        } else {
            System.out.println("Invalid index. Task not marked as complete.");
        }
    }
}

public class TaskSwing {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("TaskSwing - Java Swing-based Task Manager Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 16);

        JTextArea taskTextArea = new JTextArea();
        taskTextArea.setEditable(false);

        JTextField inputField = new JTextField();
        inputField.setFont(timesNewRomanFont);

        JButton addButton = new JButton("Add Task");
        addButton.setFont(timesNewRomanFont);

        JButton listButton = new JButton("List Tasks");
        listButton.setFont(timesNewRomanFont);

        JButton markCompleteButton = new JButton("Mark as Complete");
        markCompleteButton.setFont(timesNewRomanFont);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(timesNewRomanFont);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);

        JTextArea projectInfo = new JTextArea("Project Information:\n", 10, 30);
        projectInfo.setEditable(false);
        projectInfo.setWrapStyleWord(true);
        projectInfo.setBackground(Color.BLACK);
        projectInfo.setForeground(Color.GREEN);

        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new GridLayout(1, 2));
        taskPanel.setBackground(Color.BLACK);

        taskPanel.add(inputField);
        taskPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.setBackground(Color.BLACK);

        buttonPanel.add(listButton);
        buttonPanel.add(markCompleteButton);
        buttonPanel.add(exitButton);

        panel.add(taskPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(taskTextArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String description = inputField.getText();
                new TaskManager().addTask(description);
                inputField.setText("");
            }
        });

        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskTextArea.setText(""); // Clear previous content
                ArrayList<Task> tasks = new TaskManager().getTasks();
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    taskTextArea.append(task.description + " - " + (task.completed ? "Completed" : "Incomplete") + "\n");
                }
            }
        });

        markCompleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter the index of the task to mark as complete:");
                try {
                    int index = Integer.parseInt(input);
                    new TaskManager().markTaskAsComplete(index);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid index.");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting the program. Goodbye!");
                System.exit(0);
            }
        });

        JPanel teamPanel = new JPanel();
        teamPanel.setLayout(new GridLayout(1, 1));
        teamPanel.setBackground(Color.BLACK);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(projectInfo), BorderLayout.WEST);
        frame.add(teamPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
