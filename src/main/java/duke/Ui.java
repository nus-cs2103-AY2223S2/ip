package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    /**
     * Returns a Ui object that
     */
    public Ui() {
        sc = new Scanner(System.in);
        print("I am Duke.\nHow may I be of service?");
    }

    /**
     * Prints to System.out a border to signify Duke is speaking
     */
    private void frame() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints to System.out the
     */
    private void inner(String text) {
        for (String subStr : text.split("\n", -1)) {
            System.out.println("\t" + subStr);
        }
    }

    /**
     * Prints to System.out the text, formatted to show it was done written by Duke
     *
     * @param text A String containing a message duke would say
     */
    public void print(String text) {
        frame();
        inner(text);
        frame();
    }

    /**
     * Prints to System.out the text, formatted to show it was done written by Duke
     *
     * @param texts A String array containing a messages duke would say.
     */
    public void print(String[] texts) {
        frame();
            for (String subStr : texts) {
                inner(subStr);
            }
        frame();
    }

    /**
     * Ask the Ui to recieve input from the user
     *
     * @return String of user input
     */
    public String nextLine() {
        return sc.nextLine();
    }

}
