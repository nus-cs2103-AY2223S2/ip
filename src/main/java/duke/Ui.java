package duke;

import java.util.ArrayList;
import java.util.Scanner;

// class Ui - handles all the lines to be printed and all the user inputs (commands)
public class Ui {

    public Ui() {

    }

    public void showWelcome() {
        System.out.println("    Hi! I'm Samantha\n    How can I help?");
    }

    public void takeCommands(TaskList t) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        ArrayList<Task> tasks = t.getTasks();
        int taskCounter = tasks.size();
        while (!s.equals("bye")) {
            try {
                // method handleCommand handles current command, returns updated taskCounter variable
                taskCounter = handleCommand(s, tasks, taskCounter);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // take in next command
            s = sc.nextLine();
        }

        System.out.println("    Bye. Hope to see you soon!");
    }

    public static int handleCommand(String s, ArrayList<Task> tasks, int taskCounter) {
        // user enters list command
        if (s.contains("list")) {
            if (tasks.isEmpty()) {
                System.out.println("    You have no tasks");
            } else {
                for (int i = 0; i < taskCounter; i++) {
                    Task task = tasks.get(i);
                    System.out.println("    " + (i + 1) + ". " + task);
                }
            }

            // user enters mark or unmark command
        } else if (s.contains("mark") || s.contains("unmark")) {
            int taskNumber = Integer.parseInt(s.substring(s.length() - 1)) - 1;
            tasks.get(taskNumber).toggleMarked();
            if (s.contains("unmark")) {
                System.out.println("    OK, I've marked this task as not done yet:");
            } else {
                System.out.println("    Nice! I've marked this task as done:");
            }
            System.out.println("  " + tasks.get(taskNumber).toString());

            // user enters a new task
        } else if (s.contains("todo")) {
            if (s.substring(4).isBlank()) {
                System.out.println("    OOPS!!! The description of a todo cannot be empty.");
            } else {
                Task newTask = new Todo(s.substring(5));
                tasks.add(newTask);
                System.out.println("    added: " + newTask);
                return taskCounter + 1;
            }

        } else if (s.contains("deadline")) {
            if (s.substring(8).isBlank()) {
                System.out.println("    OOPS!!! The description of a deadline cannot be empty.");
            } else {
                String by = s.substring(s.indexOf("/") + 4);
                Task newTask = new Deadline(s.substring(9, s.indexOf("/") - 1), by);
                tasks.add(newTask);
                System.out.println("    added: " + newTask);
                return taskCounter + 1;
            }

        } else if (s.contains("event")) {
            if (s.substring(5).isBlank()) {
                System.out.println("    OOPS!!! The description of a event cannot be empty.");
            } else {
                String from = s.substring(s.indexOf("/") + 6, s.lastIndexOf("/") - 1);
                String to = s.substring(s.lastIndexOf("/") + 4);
                Task newTask = new Event(s.substring(6, s.indexOf("/") - 1), from, to);
                tasks.add(newTask);
                System.out.println("    added: " + newTask);
                return taskCounter + 1;
            }

        } else if (s.contains("delete")) {
            if (s.substring(6).isBlank()) {
                System.out.println("    OOPS!!! You have not entered anything to delete.");
            } else {
                int taskNumber = Integer.parseInt(s.substring(s.length() - 1)) - 1;
                System.out.println("    Noted. I've removed this task:\n      " + tasks.get(taskNumber) +
                        "\n    Now you have " + (taskCounter - 1) + " tasks in the list");
                tasks.remove(taskNumber);
                return taskCounter - 1;
            }
        } else {
            //throw new duke.DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
            return taskCounter;
        }
        return taskCounter;
    }
}
