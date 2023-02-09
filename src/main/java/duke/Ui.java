package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Interacts with the user in the form of printing and retrieving information
 */
public class Ui {
    private final Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads string from the terminal
     * @return string input by user
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the welcome page of the chatbot
     * @return returns welcome string
     */
    public static String showWelcome() {
//        String output = String.format("Hello from\n%s\nWhat can I do for you", LOGO);
        String welcome = "Hello im duke what can I do for you?\n";
        String requestFilepath = "By default I store data in ./text.txt, do you want me to save it elsewhere?\n";
        String hint = "do 'save <filepath>' to specify the filepath, or else just carry on";
        String output = welcome + requestFilepath + hint;
        System.out.println(output);
        return output;
    }

    /**
     * Print the delimiter between each command and outputs
     * @return the line
     */
    public String showLine() {
        String output = "-".repeat(20);
        System.out.println(output);
        return output;
    }

    /**
     * Print string to inform user that the task has been marked
     * @param task the task that has been marked
     * @return string of the task that has been marked
     */
    public String showMarked(Task task) {
        String output = String.format("Nice! I've marked this task as done:%s", task);
        System.out.println(output);
        return output;
    }

    /**
     * Print the task that has been added
     * @param task the task that has been added
     * @param tasks the list of all the tasks
     * @return task added
     */
    public String showTaskAdded(Task task, TaskList tasks) {
        String output = String.format("Got it. I've added this task:\n%s", task.toString());
        output += String.format("\nNow you have %d tasks in the list.", tasks.size());
        System.out.println(output);
        return output;
    }

    /**
     * Prints the task that has been deleted
     * @param task the task that has been deleted
     * @return formatted string of stored file
     */
    public String showDeleted(Task task) {
        String output = String.format("This task has been deleted successfully\n%s", task.toString());
        System.out.println(output);
        return output;
    }

    /**
     * Formats and Prints the tasks that has been stored
     * @param tasks the list of all the tasks
     * @return formatted string of stored file
     */
    public String showStored(Storage storage, TaskList tasks) {
        String output = String.format("%s has been updated\nThese are your tasks\n%s",storage.filepath, tasks.toFormattedString());
        System.out.println(output);
        return output;
    }

    /**
     * Print the command exception and the command that caused the exception
     * @param word command that the user input
     * @param exception the command exception that was thrown
     * @return formatted string of command exception
     */
    public String showCommandError(String word, Exception exception) {
        String output = String.format("%s\nCommand: %s", exception.toString(), word);
        System.out.println(output);
        return output;
    }

    /**
     * Print string to inform the user of a wrong command
     * @return formatted string of loading error
     */
    public String showLoadingError() {
        String output = "☹ OOPS!!! I'm sorry, but I cannot find the directory!";
        System.out.println(output);
        return output;
    }

    /**
     * Prints the ending
     * @return formatted string to end chatbot
     */
    public String showGoodbye() {
        String output = "Bye. Hope to see you again soon!";
        System.out.println(output);
        return output;
    }

    /**
     * Prints all task in tasks
     * @param tasks list of all tasks
     * @return all the task in tasks
     */
    public String showAll(TaskList tasks) {
        String output = tasks.toFormattedString();
        System.out.println(output);
        return output;
    }
}
