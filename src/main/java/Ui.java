import java.util.Scanner;

public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    private final String HELP_STR = "What can I help you with?";
    private final String BYE_STR = "Bye. Hope to see you again soon!";
    private final String TASK_ADDED = "I've added this task to your list:";

    private Scanner s;

    //print welcome message
    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(HELP_STR);
    }

    // create a Scanner object to read user input
    public Ui() {
        this.s = new Scanner(System.in);
    }

    public String getInput() {
        return (s.nextLine());
    }

    public void showGoodbye() {
        System.out.println(BYE_STR);
        s.close();
    }

    public void showTaskAdded(Task task) {
        System.out.println(TASK_ADDED);
        System.out.println(task);
    }

    public void showLoadingError() {
        System.out.println("There was an error in loading your file!");
    }

    public void showGenericError() {
        System.out.println("Error!");
    }
}
