package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Ui handles all interaction with the user.
 *
 * @author      Oskar Lew
 * @version     0.1
 * @since       0.1
 */
public class Ui {
    private static final String LOGO_AND_GREETINGS = "Hello! I'm Duke\n"
            + "What can I do for you?";
    private Scanner scanner;

    /**
     * Constructor of Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints to console the greeting message.
     * @return The opening message.
     */
    public String openingMsg() {
        return LOGO_AND_GREETINGS;
    }

    /**
     * Returns the user input as a String.
     *
     * @return echo The line entered by the user.
     */
    public String getLine() {
        String echo = "";
        if (scanner.hasNext()) {
            echo = scanner.nextLine();
        }
        return echo;
    }

    /**
     * Prints to console the goodbye message.
     * @return The exit message.
     */
    public String goodBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints to console the current task list.
     * @param tasks The list of tasks.
     * @return Message showing the list of tasks.
     */
    public String showList(TaskList tasks) {
        StringBuilder listTask = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            listTask.append(i + 1)
                    .append(".")
                    .append(tasks.get(i).toString())
                    .append(" ")
                    .append(tasks.get(i).listTag())
                    .append("\n");
        }
        return listTask.toString();
    }

    /**
     * Method to let the user know that
     * there are currently no task in the list.
     * @return No task message.
     */
    public String noTask() {
        return "There are currently no tasks in your list!";
    }

    /**
     * Method that prints to console the tasks
     * with descriptions that contains the
     * targeted word.
     * @param tasks List of tasks.
     * @return Message showing a list of tasks with the key word.
     */
    public String showListWithMatchedWords(TaskList tasks) {
        StringBuilder listWithMatchedWords = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            listWithMatchedWords.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return listWithMatchedWords.toString();
    }

    /**
     * Method to print to console when there
     * are no tasks with descriptions that contains
     * the targeted word.
     * @param word The targeted word.
     * @return Message saying that is no matching words found.
     */
    public String noMatchingWords(String word) {
        return String.format("There are no task that contains the word: %s", word);
    }

    /**
     * Method to print to console an error
     * message when there is no valid word
     * after the find command.
     * @return Error message for the find command.
     */
    public String findError() {
        return "☹ OOPS!!! The keyword is not valid!";
    }

    /**
     * Prints to console the task that was marked.
     *
     * @param tasks The list of tasks.
     * @param index The index of the task in the task list.
     * @return The marked task.
     */
    public String markMsg(TaskList tasks, int index) {
        return "Nice! I've marked this task as done:\n"
                + tasks.get(index).toString();
    }

    /**
     * Prints to console error message when
     * there is no/an invalid number
     * after the mark command.
     * @return Error message for the mark command.
     */
    public String markError() {
        return "☹ OOPS!!! A valid number has to follow the mark command!";
    }

    /**
     * Prints to console the task that was unmarked.
     * @param tasks The list of tasks.
     * @param index The index of task in the task list.
     * @return Message showing the task being unmarked.
     */
    public String unmarkMsg(TaskList tasks, int index) {
        return "OK, I've marked this task as not done yet:\n"
                + tasks.get(index).toString();
    }

    /**
     * Prints to console error message when
     * there is no/an invalid number
     * after the unmark command.
     * @return Error message from the unmark command.
     */
    public String unmarkError() {
        return "☹ OOPS!!! A valid number has to follow the unmark command!";
    }

    /**
     * Prints to console a message to indicate that
     * a task was added to the task list.
     *
     * @param tasks The list of tasks.
     * @return Message showing the added task.
     */
    public String addMsg(TaskList tasks) {
        return "Got it. I've added this task:\n"
                + tasks.get(tasks.size() - 1).toString()
                + "\n"
                + String.format("Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Prints to console a message to indicate that
     * a task was removed from the task list.
     * @param task The task being removed.
     * @param size Size of the current tasklist.
     * @return Message showing the deleted task.
     */
    public String deleteMsg(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + task.toString()
                + "\n"
                + String.format("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Prints to console an error message when
     * there is no description after the todo command.
     * @return Error message when the description is empty.
     * */
    public String todoError() {
        return "☹ OOPS!!! The description cannot be empty!";
    }

    /**
     * Prints to console an error message when
     * there is no description or timing after the
     * event command.
     * @return Message when there is an error with the event command.
     */
    public String eventError() {
        return "☹ OOPS!!! The timing was not specified or there was no description!";
    }

    /**
     * Prints to console an error message when
     *  there is no description or timing after
     *  the deadline command.
     * @return Message when there is an error with the deadline command.
     */
    public String deadlineError() {
        return "☹ OOPS!!! The timing needs to be in format yyyy-mm-dd hhmm"
                + " or there was no description!";
    }

    /**
     * Prints to console an error message when
     * there is no number after the delete command.
     * @return Message when there is an error with the delete command.
     */
    public String deleteError() {
        return "☹ OOPS!!! A valid number has to follow the delete command.";
    }

    /**
     * Prints to console an error message when
     * an unknown command is parsed.
     * @return Message when there is an unknown command.
     */
    public String unknownMsg() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    /**
     * Methods to confirm the tags
     * that are added to the task.
     * @param tasks The task list.
     * @param index The index of chosen task.
     * @return Confirmation message.
     */
    public String updateTag(TaskList tasks, int index) {
        return "Ok, I've added 1 new tag to the task:\n"
                + tasks.get(index).toString()
                + " "
                + tasks.get(index).listTag()
                + "\n";
    }

    /**
     * Method to notify the user
     * that there was an error when tagging
     * the task.
     * @return The error message.
     */
    public String tagError() {
        return "☹ OOPS!!! The tag command needs should be in the following form:\n"
                + "tag {index} {tag1 tag2 ..}";
    }

    /**
     * Method to close the scanner
     * after the user ends the program.
     */
    public void endUi() {
        scanner.close();
    }
}
