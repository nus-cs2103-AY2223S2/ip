package ui;

import dukeexeption.DukeException;

public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String INTRODUCTION_MESSAGE = "Hello! I'm Duke\n"
            + "What can I do for you?";
    private final String EXITING_MESSAGE = "Bye. Hope to see you again soon!";

    public void printStartUpMessage() {
        System.out.println(this.LOGO);
        this.printFormattedResponse(this.INTRODUCTION_MESSAGE);
    }

    public void printExitingMessage() {
        this.printFormattedResponse(this.EXITING_MESSAGE);
    }

    /**
     * Prints the formatted response in the console.
     *
     * @param response the string to be printed
     */
    public void printFormattedResponse(String response) {
        final int INDENTS = 4;

        String lineAddedResponse = "____________________________________________________________\n"
                + " " + response.replace("\n", "\n" + " ")
                + "\n____________________________________________________________\n";
        String indentedResponse = " ".repeat(INDENTS)
                + lineAddedResponse.replace("\n", "\n" + " ".repeat(INDENTS));

        System.out.println(indentedResponse);
    }

    /**
     * Prints the formatted error in the console.
     *
     * @param exception the exception to be printed
     */
    public void printFormattedError(DukeException exception) {
        printFormattedResponse("☹ OOPS!!! " + exception.toString());
    }
}
