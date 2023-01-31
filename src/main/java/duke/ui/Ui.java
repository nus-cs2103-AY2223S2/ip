package duke.ui;

import java.util.Scanner;

import duke.exceptions.TaskException;

/**
 * Acts as the class to response to user inputs
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs for ui class
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Represents the start-up message
     */
    public static String welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        return ("Hello I'm \n" + logo + "What can I do for you?\n");
    }

    /**
     * Collects user inputs
     *
     * @return user inputs for each command
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays sentence used for bye command
     */
    public String byeMessage() {
        return "Oh no! Pls don't leave me.. I'm your ONLY friend.. Rmb?? :(";
    }

    /**
     * Displays sentence used in list command
     */
    public String listMessage(String input) {
        return ("Take a look at ye DREAM goals for 2023") + input;
    }

    /**
     * Prints the current size of the list
     *
     * @param size shows the current size of the list
     */
    public void listInfo(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Arranges types of exceptions under TaskException class
     *
     * @param code read type of commands in String
     * @throws TaskException return a custom error message if there is such error
     */
    public String error(String code) throws TaskException {
        switch (code) {
        case "todo":
            throw new TaskException("Please enter an to-do item");

        case "deadline":
            throw new TaskException("Enter an valid item followed by a deadline");

        case "event":
            throw new TaskException("Event item must include a start time and an end time");

        default:
            throw new TaskException("Sorry! Duke has no idea what it is as it is not an instruction");

        }
    }

}
