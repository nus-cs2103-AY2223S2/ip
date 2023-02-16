package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all the lines to be printed and all the user inputs (commands)
 */
public class Ui {

    public Ui() {

    }
    /**
     * Prints out welcome greeting to user when Duke is run
     */
    public static String showWelcome() {
        return "    Hi! I'm Samantha\n    How can I help?";
    }

    /**
     * Handles commands from the user
     */
    public static String handleCommand(String s, TaskList t) throws DukeException {
        ArrayList<Task> tasks = t.getTasks();
        int numTasks = tasks.size();
        assert numTasks >= 0 : "Number of tasks should be a positive number";
        assert s.length() >= 0 : "Number of letters in a command should be a positive number";
        // user enters list command
        if (s.contains("list")) {
            return t.displayTasks();

            // user enters mark or unmark command
        } else if (s.contains("unmark")) {
            return toggleMarked(s, t, false);

        } else if (s.contains("mark")) {
            return toggleMarked(s, t, true);

            // user enters a new task
        } else if (s.contains("todo")) {
            return addTodo(s, t);

        } else if (s.contains("deadline")) {
            return addDeadline(s, t);

        } else if (s.contains("event")) {
            return addEvent(s, t);

        } else if (s.contains("delete")) {
            return deleteTask(s, t);

        } else if (s.contains("find")) {
            return findTasks(s, t);

            // make displayTasks return a String
        } else if (s.contains("sort")) {
            return t.displaySorted();

        } else if (s.contains("bye")) {
            return "    Bye. Hope to see you soon!";

        } else {
            throw new duke.DukeException("    OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Command to toggle whether a task is done or not
     */
    public static String toggleMarked(String s, TaskList t, boolean mark) {
        int taskNumber = Integer.parseInt(s.substring(s.length() - 1)) - 1;
        if (mark && !t.get(taskNumber).getIsDone()) {
            t.get(taskNumber).toggleMarked();
        } else if (!mark  && t.get(taskNumber).getIsDone()) {
            t.get(taskNumber).toggleMarked();
        } else {
            return mark ? "    This task is already marked" :
                    "    This task is already unmarked";
        }

        String output = "";
        if (s.contains("unmark")) {
            output += "    OK, I've marked this task as not done yet:";
        } else {
            output += "    Nice! I've marked this task as done:";
        }
        return output + "  " + t.get(taskNumber).toString();
    }

    /**
     * Command to add a To-Do to the TaskList
     */
    public static String addTodo(String s, TaskList t) {
        if (s.substring(4).isBlank()) {
            return "    OOPS!!! The description of a todo cannot be empty.";
        } else {
            Task newTask = new Todo(s.substring(5));
            t.addTask(newTask);
            return "    added: " + newTask;
        }
    }

    /**
     * Command to add a Deadline to the TaskList
     */
    public static String addDeadline(String s, TaskList t) {
        if (s.substring(8).isBlank()) {
            return "    OOPS!!! The description of a deadline cannot be empty.";
        } else {
            try {
                String by = s.substring(s.indexOf("/") + 4);
                Task newTask = new Deadline(s.substring(9, s.indexOf("/") - 1), by);
                t.addTask(newTask);
                return "    added: " + newTask;
            } catch (StringIndexOutOfBoundsException e) {
                return "Incorrect command format, use: " +
                        "<Task-Name> /by <dd-MM-yyyy>";
            } catch (DateTimeParseException e) {
                return "Incorrect format for your deadline date, use: " +
                        "<dd-MM-yyyy>";
            }

        }
    }

    /**
     * Command to add an Event to the TaskList
     */
    public static String addEvent(String s, TaskList t) {
        if (s.substring(5).isBlank()) {
            return "    OOPS!!! The description of a event cannot be empty.";
        } else {
            try {
                String from = s.substring(s.indexOf("/") + 6, s.lastIndexOf("/") - 1);
                String to = s.substring(s.lastIndexOf("/") + 4);
                Task newTask = new Event(s.substring(6, s.indexOf("/") - 1), from, to);
                t.addTask(newTask);
                return "    added: " + newTask;
            } catch (StringIndexOutOfBoundsException e) {
                return "Incorrect command format, use: " +
                        "<Task-Name> /from <dd-MM-yyyy HH:mm> /to <HH:mm>";
            } catch (DateTimeParseException e) {
                return "Incorrect format for your event dates, use: " +
                        "/from <dd-MM-yyyy HH:mm> /to <HH:mm>";
            }

        }
    }

    /**
     * Command to delete a task from the TaskList
     */
    public static String deleteTask(String s, TaskList t) {
        if (s.substring(6).isBlank()) {
            return "    OOPS!!! You have not entered anything to delete.";
        } else {
            int taskNumber = Integer.parseInt(s.substring(s.length() - 1)) - 1;
            Task deletedTask = t.get(taskNumber);
            t.remove(taskNumber);
            return "    Noted. I've removed this task:\n      " + deletedTask +
                    "\n    Now you have " + t.size()+ " tasks in the list";

        }
    }

    /**
     * Command to find a Task in the TaskList
     */
    public static String findTasks(String s, TaskList t) {
        String findString = s.substring(5);
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task task : t.getTasks()) {
            if (task.toString().contains(findString)) {
                foundTasks.add(task);
            }
        }
        TaskList searchResults = new TaskList(foundTasks);
        return "Here are the tasks I found!\n" + searchResults.displayTasks();
    }
}
