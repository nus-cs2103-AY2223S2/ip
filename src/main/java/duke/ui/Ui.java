package duke.ui;

import java.util.Scanner;

/**
 * Encapsulates the user interface of ChadGPT.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class Ui {
    private Scanner sc;

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
    public static String startUp() {
        String str = "";
        str += "Whats good its CHADGpt\n";
        str += "Call me for???\n";
        str += "now i will tell u how to enter stuff so u better listen\n";
        str += "eg1 : todo stuff\n";
        str += "eg2 : deadline <name>/<time in YYYY-MM-DD\n";
        str +="eg3 : event <name>/<starttime>/<endtime>\n";
        str += "you can add a friend like this friend_name_number\n";
        str += "unfriend to delete your friend\n";
        str += "listfriends to get a list of your homies\n";
        return str;
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
