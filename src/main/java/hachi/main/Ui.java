package hachi.main;


/**
 * Ui interface that interacts with the user.
 */
public class Ui {
    static String separator = "‿୨♡୧‿‿‿‿୨♡୧‿‿‿‿୨♡୧‿";

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
