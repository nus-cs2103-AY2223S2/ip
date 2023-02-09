package duke;

import java.util.ArrayList;
import java.util.Scanner;

// class Ui - handles all the lines to be printed and all the user inputs (commands)
public class Ui {

    public Ui() {

    }

    // prints out welcome greeting to user when Duke is run
    public String showWelcome() {
        return "    Hi! I'm Samantha\n    How can I help?";
    }

    public static String handleCommand(String s, TaskList t) {
        ArrayList<Task> tasks = t.getTasks();
        int numTasks = tasks.size();
        assert numTasks >= 0 : "Number of tasks should be a positive number";
        assert s.length() >= 0 : "Number of letters in a command should be a positive number";
        // user enters list command
        if (s.contains("list")) {
            return t.displayTasks();

            // user enters mark or unmark command
        } else if (s.contains("mark") || s.contains("unmark")) {
            int taskNumber = Integer.parseInt(s.substring(s.length() - 1)) - 1;
            tasks.get(taskNumber).toggleMarked();
            String output = "";
            if (s.contains("unmark")) {
                output += "    OK, I've marked this task as not done yet:";
            } else {
                output += "    Nice! I've marked this task as done:";
            }
            return output + "  " + tasks.get(taskNumber).toString();

            // user enters a new task
        } else if (s.contains("todo")) {
            if (s.substring(4).isBlank()) {
                return "    OOPS!!! The description of a todo cannot be empty.";
            } else {
                Task newTask = new Todo(s.substring(5));
                tasks.add(newTask);
                return "    added: " + newTask;
            }

        } else if (s.contains("deadline")) {
            if (s.substring(8).isBlank()) {
                return "    OOPS!!! The description of a deadline cannot be empty.";
            } else {
                String by = s.substring(s.indexOf("/") + 4);
                Task newTask = new Deadline(s.substring(9, s.indexOf("/") - 1), by);
                tasks.add(newTask);
                return "    added: " + newTask;
            }

        } else if (s.contains("event")) {
            if (s.substring(5).isBlank()) {
                return "    OOPS!!! The description of a event cannot be empty.";
            } else {
                String from = s.substring(s.indexOf("/") + 6, s.lastIndexOf("/") - 1);
                String to = s.substring(s.lastIndexOf("/") + 4);
                Task newTask = new Event(s.substring(6, s.indexOf("/") - 1), from, to);
                tasks.add(newTask);
                return "    added: " + newTask;
            }

        } else if (s.contains("delete")) {
            if (s.substring(6).isBlank()) {
                return "    OOPS!!! You have not entered anything to delete.";
            } else {
                int taskNumber = Integer.parseInt(s.substring(s.length() - 1)) - 1;
                Task deletedTask = tasks.get(taskNumber);
                tasks.remove(taskNumber);
                return "    Noted. I've removed this task:\n      " + deletedTask +
                        "\n    Now you have " + tasks.size()+ " tasks in the list";

            }
        } else if (s.contains("find")) {
            String findString = s.substring(5);
            ArrayList<Task> foundTasks = new ArrayList<Task>();
            for (Task task : tasks) {
                if (task.toString().contains(findString)) {
                    foundTasks.add(task);
                }
            }
            TaskList searchResults = new TaskList(foundTasks);
            return "Here are the tasks I found!\n" + searchResults.displayTasks();

            // make displayTasks return a String
        } else if (s.contains("sort")) {
            return t.displaySorted();
        } else if (s.contains("bye")) {
            return "    Bye. Hope to see you soon!";
        } else {
            //throw new duke.DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            return "    OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
