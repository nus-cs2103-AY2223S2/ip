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
     * It prints Loadding error message to user
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

    public void bye() {
        Duke.lining();
        System.out.println("Bye. Hope to see you again soon!");
        Duke.lining();
    }

    public void showEmptyError() {
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
    }

    public void showFileError(){
        System.out.println("File doesn't exist!");
    }
}
