package duke.ui;

import java.util.Scanner;

/**
 * Encapsulates the user interface of ChadGPT.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class Ui {
    Scanner sc;

    /**
     * Constructs the user interface of ChadGPT.
     *
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints messages to be sent to the user to show ChadGPT has been initialized.
     *
     */
    public void startUp() {
        System.out.println("Whats good its CHADGpt");
        System.out.println("Call me for???");
        System.out.println("now i will tell u how to enter stuff so u better listen");
        System.out.println("eg1 : todo stuff");
        System.out.println("eg2 : deadline <name>/<time in YYYY-MM-DD");
        System.out.println("eg3 : event <name>/<starttime>/<endtime>\n");
    }

    /**
     * Closes scanner object.
     *
     */
    public void close() {
        sc.close();
    }

    /**
     * Reads the next line of user input.
     *
     * @return A string representation of the user's input.
     */
    public String readLine() {
        return sc.nextLine();
    }


}
