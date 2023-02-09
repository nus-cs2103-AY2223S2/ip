package ui;

import exception.DukeException;

/**
 * Represents the Ui class that handles user interactions
 */
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
     * @param strings strings that will be printed
     */
    public void printResponse(String... strings) {
        String res = "";
        String line = "\t____________________________________________________________\n";
        res += line;
        for (String s : strings) {
            res += String.format("\t %s\n", s);
        }
        res += line;
        System.out.println(res);
    }

    public void printError(DukeException e) {
        printResponse(e.toString());
    }
}
