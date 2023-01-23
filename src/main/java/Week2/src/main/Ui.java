package Week2.src.main;

/**
 * Deals with interactions with the user
 */
public class Ui {

    /**
     * Ui Constructor
     */
    public Ui(){

    }

    /**
     * Prints hello message to user
     */
    public void hello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke.lining();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Duke.lining();
    }

    /**
     * It prints Loadding error message to user
     */
    public void showLoadingError() {
        System.out.println("Loading...");
    }
}
