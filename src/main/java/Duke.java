/**
 * The Duke class implements a personal assistant chatbot that helps the user to keep track of various tasks.
 *
 * @author Chia Jeremy
 */

public class Duke {

    private static final String LOGO
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static void greet() {
        System.out.println("Hello I'm\n" + LOGO + "What can I do for you?\n");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        greet();
        exit();
    }
}

