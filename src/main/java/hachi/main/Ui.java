package hachi.main;


import hachi.tasks.Task;

/**
 * Ui interface that interacts with the user.
 */
public class Ui {
    private static final String separator = "‿୨♡୧‿‿‿‿୨♡୧‿‿‿‿୨♡୧‿";

    public String showExitMessage() {
        return separator + "\n" + "\n" + "   Ciao ~ see you again soon!";
    }

    public String showDeleted(TaskList tasks, Task task) {
        return separator + "\n" + "\n" + "   okie dokie. I've removed this task:\n" + task +
                "   Now you have " + tasks.size() + " tasks in the list.";
    }

    public String showAdded(TaskList tasks, Task task) {
        return separator + "\n" + "\n" + " okie dokie. I've added this task:" + "\n" + task +
                "   Now you have " + tasks.size() + " tasks in the list.";
    }

    public String noTasksMessage() {
        return separator + "\n" + "\n" + "You don't have any tasks at the moment...";
    }

    public String noDeadlineMessage() {
        return separator + "\n" + "\n" + "Ohno! seems like you forgot to put a deadline.";
    }

    public String emptyDescription() {
        return separator + "\n" + "\n" + "Ohno! The description cannot be empty.";
    }

    public String wrongDeadlineFormat() {
        return separator + "\n" + "\n" + "Key in deadline in the format of yyyy-mm-dd";
    }

    public String invalidIndexMessage() {
        return separator + "\n" + "\n" + "Ohno! I don't know which task you are referring to :(";
    }

    public String noStartingTime() {
        return separator + "\n" + "\n" + "Ohno! seems like you forgot to put the time of the event ";
    }

    public String noEndingTime() {
        return separator + "\n" + "\n" + " Ohno! seems like you forgot to put the ending time of the event ";
    }

    /**
     * Prints an error message if it fails to load saved tasks in storage.
     */
    public void showLoadingError() {
        System.out.println("Unable to load tasks from storage");
    }

    /**
     * Prints the welcome message.
     */
    public static String welcomeMessage() {

        String intro = "   Hello I'm hachi\n   What can I do for you today?\n";

        String tasks = "\n   hachi can do these for you" +
                "\n   ♡ list               \n| View your to-do list" +
                "\n   ♡ todo \"task\"         \n| Add a task to your to-do list" +
                "\n   ♡ deadline \"task\" /by \"yyyy-mm-dd\"   \n| Add a task to complete by the specified deadline" +
                "\n   ♡ event \"event\" /from \"yyyy-mm-dd\" /to \"yyyy-mm-dd\"  \n| Add an event on the specified date" +
                "\n   ♡ mark \"num\"                     \n| Mark the (num)th item in your list as completed" +
                "\n   ♡ unmark \"num\"                     \n| Mark the (num)th item in your list as uncompleted" +
                "\n   ♡ bye                                \n| Quit hachi\n";

        return separator + "\n" + "\n" + intro + "\n" + separator +  "\n" + tasks + "\n" + separator;
    }



}
