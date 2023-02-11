package duke;

/**
 * Class to define the methods used to interact with user
 */
public class Ui {

    /**
     * Prints greeting text
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("How may I help you today?\n");
    }

    /**
     * Prints the user's input
     */
    public static void echo(String userInput) {
        System.out.println("> Duke's response:");
        System.out.println(userInput);
        System.out.println("--------------------------------\n");
    }

    /**
     * Prints exit message
     */
    public static void exit() {
        System.out.println("> Duke's response:");
        System.out.println("Bye. Hope to see you again!");
        System.out.println("--------------------------------\n");
    }

}
