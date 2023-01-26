/**
 * Ui deals with interactions with the user
 */
public class Ui {
    private final String LINE = "____________________________________________________________";
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui () {
    }

    public void line() {
        System.out.println(LINE);
    }

    public void greeting() {
        System.out.println(LOGO);
        line();
        System.out.println("Hello from\n" + LOGO);
        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line();
    }

    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    public void somethingWentWrong() {
        System.out.println("Invalid response. Please try: todo, deadline or event. :)");
    }

}
