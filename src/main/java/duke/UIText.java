package duke;

import java.util.Scanner;

public class UIText {
    private static final String GREET = "Hello! I am Kevin, an interactive chatbot. What can I do for you?" + "\n";
    private static final String EXIT = "Bye! Hope to talk to you again!";
    private static final String SEPARATOR = "____________________________________________________________";
    private Scanner sc;
    public UIText() {
        this.sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine();
    }

    /**
     * String representation of opening message.
     * @return String object of greeting message.
     */
    public String greet() {
        return GREET;
    }
    /**
     * String representation of closing message and closes the scanner. 
     * @return String object of greeting message.
     */
    public String exit() {
        sc.close();
        return EXIT;
    }

    public String separate() {
        return SEPARATOR + "\n";
    }
}
