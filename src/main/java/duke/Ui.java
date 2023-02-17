package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


// class Ui - handles all response messages to every user input (commands)
public class Ui {

    public Ui() {

    }

    // prints out welcome greeting to user when Duke is run
    public static String greet() {
        return "    Hi! I'm Duke\n    How can I help?";
    }

    public static String displayTasks(TaskList tasks) {
        String s = "";
        if (tasks.size() == 0) {
            s += "    No tasks";
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                s += "    " + (i + 1) + ". " + task;
                s += i != tasks.size() ? "\n" : "";
            }
        }
        return s;
    }

    public static String displayMarked(int taskNumber, TaskList tasks) {
        String output = "";
        output += "    Nice! I've marked this task as done:\n";
        return output + "  " + tasks.get(taskNumber).toString();

    }

    public static String displayUnmarked(int taskNumber, TaskList tasks) {
        String output = "";
        output += "    OK, I've marked this task as not done yet:\n";
        return output + "  " + tasks.get(taskNumber).toString();

    }

    public static String displayTask(Task task) {
        return "   added: " + task;
    }
    public static String displayFind(TaskList tasks) {
        return "Here are the tasks I found!\n" + displayTasks(tasks);
    }

    public static String displaySnooze(String d, String newD) {
        return d + " has been snoozed to " + newD;
    }
}
