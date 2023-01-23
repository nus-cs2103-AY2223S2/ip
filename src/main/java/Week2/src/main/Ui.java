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
     * Prints out that it is loading when it faces a loading error
     */
    public void showLoadingError() {
        System.out.println("Loading...");
    }

    /**
     * First interaction with user
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
}
