package duke.main;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import duke.task.*;

/**
 * Prints responses to commands.
 */
public class Ui {

    Scanner sc = new Scanner(System.in);

    /**
     * Prints greeting message when app is started.
     */
    public void printGreetingMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________" +
                "\n\t Hello! I'm Duke\n" +
                "\t What can I do for you?" +
                "\n\t____________________________________________________________");
    }

    /**
     * Prints a list of current tasks.
     */
    public void printCommandList(List<Task> allTasks) {
        DateTimeFormatter dateTimeFormatter1 =
                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm a");
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < allTasks.size(); i++) {
            int numbering = i + 1;
            Task task = allTasks.get(i);
            String time = "";
            if (task.getTaskType().equals("[D]")) {
                time = " (by: " +
                        task.getDeadline().format(dateTimeFormatter1) + ")";
            } else if (task.getTaskType().equals("[E]")) {
                time = " (from: " +
                        task.getEventStartTime().format(dateTimeFormatter1)
                        + " to: "
                        + task.getEventEndTime().format(dateTimeFormatter1)
                        + ")";
            }
            System.out.println("\t " + numbering + "." +
                    task.getTaskType() + task.getTaskStatus() + " "
                    + task.getTask() + time);
        }

        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints a list of deadlines or events happening on this day.
     */
    public void printDeadlineOrEventsOnDay(LocalDate dateTime, TaskList allTasks) {
        DateTimeFormatter dateTimeFormatter1 =
                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm a");
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list at this day:");
        int numbering = 1;
        for (int i = 0; i < allTasks.getNumberOfTask(); i++) {
            Task task = allTasks.getTask(i);
            String time = "";
            if (task.getTaskType().equals("[D]") &&
                    task.getDate().equals(dateTime)) {
                time = " (by: " +
                        task.getDeadline().format(dateTimeFormatter1) + ")";
            } else if (task.getTaskType().equals("[E]") &&
                    task.getDate().equals(dateTime)) {
                time = " (from: " +
                        task.getEventStartTime().format(dateTimeFormatter1)
                        + " to: "
                        + task.getEventEndTime().format(dateTimeFormatter1)
                        + ")";
            } else {
                continue;
            }
            System.out.println("\t " + numbering + "." +
                    task.getTaskType() + task.getTaskStatus() + " "
                    + task.getTask() + time);
            numbering += 1;
        }
    }

    /**
     * Prints exit message when app is terminated.
     */
    public void printByeMessage() {
        System.out.println("\t____________________________________________________________" +
                "\n\t Bye. Hope to see you again soon!" +
                "\n\t____________________________________________________________");
    }

    /**
     * Reads command from user.
     */
    public String getCommand() {
        return sc.nextLine();
    }

    /**
     * Print any file loading error message.
     */
    public void showLoadingError(String message) {
        System.out.println(message);
    }
}
