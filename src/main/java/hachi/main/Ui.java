package hachi.main;


import hachi.tasks.Task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Ui interface that interacts with the user.
 */
public class Ui {
    private static final String separator = "‿୨♡୧‿‿‿‿୨♡୧‿‿‿‿୨♡୧‿";

    /**
     * Informs user that a task has been deleted.
     *
     * @param tasks Updated TaskList.
     * @param task Task deleted.
     */
    public String showDeleted(TaskList tasks, Task task) {
        return separator + "\n" + "\n" + "   okie dokie. I've removed this task:\n" + task + "\n" +
                "   Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Informs user that a task has been added.
     *
     * @param tasks Updated TaskList.
     * @param task Task deleted.
     */
    public String showAdded(TaskList tasks, Task task) {
        return separator + "\n" + "\n" + " okie dokie. I've added this task:" + "\n" + task + "\n" + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Displays the exit message when exiting.
     */
    public String showExitMessage() {
        return separator + "\n" + "\n" + "   Ciao ~ see you again soon!";
    }

    /**
     * Informs user that the TaskList is empty.
     */
    public String noTasksMessage() {
        return separator + "\n" + "\n" + "You don't have any tasks at the moment...";
    }

    /**
     * Informs user that the there is no time input.
     */
    public String noDeadlineMessage() {
        return separator + "\n" + "\n" + "Ohno! seems like you forgot to put a deadline.";
    }

    /**
     * Informs user that the input description is empty.
     */
    public String emptyDescription() {
        return separator + "\n" + "\n" + "Ohno! The description cannot be empty.";
    }

    /**
     * Informs user that the format of time of a Deadline Task is wrong.
     */
    public String wrongDeadlineFormat() {
        return separator + "\n" + "\n" + "Key in deadline in the format of yyyy-mm-dd";
    }

    /**
     * Informs user that the index specified of the input does not exist.
     */
    public String invalidIndexMessage() {
        return separator + "\n" + "\n" + "Ohno! I don't know which task you are referring to :(";
    }

    /**
     * Informs user that the starting time of a Event Task does not exist.
     */
    public String noStartingTime() {
        return separator + "\n" + "\n" + "Ohno! seems like you forgot to put the time of the event ";
    }

    /**
     * Informs user that the ending time of a Event Task does not exist.
     */
    public String noEndingTime() {
        return separator + "\n" + "\n" + " Ohno! seems like you forgot to put the ending time of the event ";
    }

    /**
     * Prints an error message if it fails to load saved tasks in storage.
     */
    public String showLoadingError() {
        return separator + "\n" + "\n" + "Unable to load tasks from storage";
    }

    /**
     * Prints an error message if it fails to save tasks in storage.
     */
    public String showSavingError() {
        return separator + "\n" + "\n" + "Unable to save tasks to storage";
    }


    /**
     * Prints the welcome message.
     */
    public String welcomeMessage() {

        String intro = "   Hello I'm hachi\n   What can I do for you today?\n";

        intro += "\n   hachi can do these for you" +
                "\n   ♡ list               \n| View your to-do list" +
                "\n   ♡ todo \"task\"         \n| Add a task to your to-do list" +
                "\n   ♡ deadline \"task\" /by \"yyyy-mm-dd\"   \n| Add a task to complete by the specified deadline" +
                "\n   ♡ event \"event\" /from \"yyyy-mm-dd\" /to \"yyyy-mm-dd\"  \n| Add an event on the specified date" +
                "\n   ♡ mark \"num\"                     \n| Mark the (num)th item in your list as completed" +
                "\n   ♡ unmark \"num\"                     \n| Mark the (num)th item in your list as uncompleted" +
                "\n   ♡ bye                                \n| Quit hachi\n";
        intro += getSavedString();

        return separator + "\n" + "\n" + intro + "\n" + separator ;
    }

    /**
     * Provides saved tasks in the text file.
     *
     * @return String representation of tasks saved in the text file.
     */
    private String getSavedString() {
        String str = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("hachi.txt"));
            Stream<String> contentStream = reader.lines();
            List<String> contentList = contentStream.collect(Collectors.toList());

            for (String content : contentList) {
                str += "\n" + content;
            }
        } catch (FileNotFoundException e) {
            return "no file";
        }
        return str;
    }

    /**
     * Prints out the TaskList.
     *
     * @param tasks
     * @return A TaskList.
     */
    public String printList(TaskList tasks) {
        return separator + "\n" + "\n" + "Here are the tasks in your list: \n" + "\n" + tasks + "\n" + separator ;
    }





}
