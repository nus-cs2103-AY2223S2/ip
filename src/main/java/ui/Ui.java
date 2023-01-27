package ui;

import exception.DukeException;

public class Ui {
    private final String intro = "Hello! I'm Duke\n\t What can I do for you?";
    private final String extStr = "Bye! Hope to see you again soon!";

    public Ui() {}

    public void printIntro() {
        printResponse(intro);
    }

    public void printExit() {
        printResponse(extStr);
    }

    /**
     * Prints formatted response to the console.
     *
     * @param s string that will be printed
     */
    public void printResponse(String s) {
        String p = String.format("\t____________________________________________________________\n" +
                "\t %s\n" +
                "\t____________________________________________________________\n", s);
        System.out.println(p);
    }

    public void printError(DukeException e) {
        printResponse(e.toString());
    }
}
