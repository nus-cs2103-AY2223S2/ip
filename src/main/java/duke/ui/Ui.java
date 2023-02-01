package duke.ui;

import java.util.Scanner;

/**
 * Represents the user interface of Duke. Currently, just terminal text.
 */
public class Ui {

    Scanner ui;
    String response;

    public Ui() {
        this.ui = new Scanner(System.in);
    }

    /**
     * @return the next command inputted by the user.
     */
    public String nextCommand() {
        return this.ui.nextLine();
    }

    /**
     * @return true if there is another command by the user, false if not.
     */
    public boolean hasNextCommand() {
        return this.ui.hasNextLine();
    }

    /**
     * Stores the response given by Duke.
     *
     * @param reply the reply given by Duke.
     */
    public void response(String reply) {
        this.response = reply;
    }

    /**
     * Prints out the response given by Duke.
     */
    public void showResponse() {
        System.out.println(this.response);
    }
}
