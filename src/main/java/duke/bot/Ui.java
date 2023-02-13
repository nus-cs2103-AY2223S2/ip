package duke.bot;


import javafx.application.Application;

/**
 * UI class for displaying user interface.
 */
public class Ui {

    /*Constructor for Ui*/
    public Ui () {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Greet();
    }


    /*Code which prompts user input to start main driver code in Duke*/
    public static void Greet() {
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you? "+
                "\n('bye' to terminate duke.Duke)" +
                "\n('list' to access list of tasks)" +
                "\n('un/mark X' to un/mark X task on list)" +
                "\n('todo/deadline/event' for keeping note of different tasks)");
    }

    /*Code informs user that a saved file has been created for user input*/
    public void showLoadingError(){
        System.out.println("A new text file has been created under data.");
    }
}
