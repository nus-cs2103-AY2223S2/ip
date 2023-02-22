package duke.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;

import duke.task.*;

/**
 * Prints responses to commands.
 */
public class Ui {

    Ui() { }
    /**
     * Prints greeting message when app is started.
     *
     * @return String of greeting response.
     */
    public String printGreetingMessage() {
        String output;
        String logo;
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        output = "Hello from\n" + logo;
        output += "\n\t Hello! I'm Duke\n"
                + "\t What can I do for you?";
        return output;
    }

    /**
     * Prints a list of current tasks.
     *
     * @param allTasks List of all existing tasks.
     * @return String of existing list of tasks.
     */
    public String printCommandList(List<Task> allTasks) {
        DateTimeFormatter dateTimeFormatter1 =
                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm a");

        String output;
        output = "\n\t Here are the tasks in your list:";
        for (int i = 0; i < allTasks.size(); i++) {
            int numbering = i + 1;
            Task task = allTasks.get(i);
            String time = "";
            if (task.getTaskType().equals("[D]")) {
                time = " (by: "
                        + task.getDeadline().format(dateTimeFormatter1) + ")";
            } else if (task.getTaskType().equals("[E]")) {
                time = " (from: " + task.getEventStartTime().format(dateTimeFormatter1)
                        + " to: " + task.getEventEndTime().format(dateTimeFormatter1) + ")";
            }
            output += "\n\t " + numbering + "." + task.getTaskType() + task.getTaskStatus() + " "
                    + task.getTask() + time;
        }
        return output;
    }

    /**
     * Prints a list of deadlines or events happening on this day.
     *
     * @param dateTime Date that user wants to find tasks on.
     * @param allTasks List of all existing tasks.
     * @return String of list of deadlines or events due on stated day.
     */
    public String printDeadlineOrEventsOnDay(LocalDate dateTime, TaskList allTasks) {
        DateTimeFormatter dateTimeFormatter1 =
                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm a");

        String output;
        output = "\n\t Here are the tasks in your list at this day:";
        int numbering = 1;
        for (int i = 0; i < allTasks.getNumberOfTask(); i++) {
            Task task = allTasks.getTask(i);
            String time = "";
            if (task.getTaskType().equals("[D]") && task.getDate().equals(dateTime)) {
                time = " (by: "
                        + task.getDeadline().format(dateTimeFormatter1) + ")";
                output += "\n\t " + numbering + "."
                        + task.getTaskType() + task.getTaskStatus() + " "
                        + task.getTask() + time;
                numbering += 1;
            } else if (task.getTaskType().equals("[E]") && task.getDate().equals(dateTime)) {
                time = " (from: "
                        + task.getEventStartTime().format(dateTimeFormatter1)
                        + " to: "
                        + task.getEventEndTime().format(dateTimeFormatter1)
                        + ")";
                output += "\n\t " + numbering + "."
                        + task.getTaskType() + task.getTaskStatus() + " "
                        + task.getTask() + time;
                numbering += 1;
            }
        }
        return output;
    }

    /**
     * Prints a list of tasks contains keyword.
     *
     * @param keyword Keyword that user wants to search for relevant
     *                tasks.
     * @param allTasks List of all existing tasks.
     * @return String of tasks relating to keyword.
     */
    public String printFindResults(String keyword, TaskList allTasks) {
        DateTimeFormatter dateTimeFormatter1 =
                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm a");

        String output;
        output = "\n\t Here are the matching tasks in your list:";
        int numbering = 1;
        for (int i = 0; i < allTasks.getNumberOfTask(); i++) {
            Task task = allTasks.getTask(i);
            String time = "";
            if (task.getTaskType().equals("[D]") &&
                    task.getTask().contains(keyword)) {
                time = " (by: "
                        + task.getDeadline().format(dateTimeFormatter1) + ")";
                output += "\n\t " + numbering + "."
                        + task.getTaskType() + task.getTaskStatus() + " "
                        + task.getTask() + time;
                numbering += 1;
            } else if (task.getTaskType().equals("[E]") &&
                    task.getTask().contains(keyword)) {
                time = " (from: "
                        + task.getEventStartTime().format(dateTimeFormatter1)
                        + " to: "
                        + task.getEventEndTime().format(dateTimeFormatter1)
                        + ")";
                output += "\n\t " + numbering + "."
                        + task.getTaskType() + task.getTaskStatus() + " "
                        + task.getTask() + time;
                numbering += 1;
            }
        }
        return output;
    }

    /**
     * Prints exit message when app is terminated.
     *
     * @return String of exit response.
     */
    public String printByeMessage() {
        String output;
        output = "\n\t Bye. Hope to see you again soon!";
        return output;
    }

    public void showLoadingError(String message) {
        System.out.println(message);
    }
}
