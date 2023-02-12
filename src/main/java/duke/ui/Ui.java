package duke.ui;

import duke.exceptions.TaskException;

/**
 * Acts as the class to response to user inputs
 */
public class Ui {

    /**
     * Represents the start-up message
     *
     * @return a start-up message
     */
    public static String welcomeMessage() {
        String logo = " ____        _       \n"
                + "|  _ \\_   _| |   ___ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\_,_|_|\\_\\___|\n";

        return ("Hello I'm \n" + logo + "What can I do for you?\n");
    }

    /**
     * Displays sentence used for bye command
     *
     * @return a bye message before app closes
     */
    public String byeMessage() {
        return "Oh no! Pls don't leave me.. I'm your ONLY friend.. Rmb?? :(";
    }

    /**
     * Displays sentence used in list command
     *
     * @param input passes in a input of type string
     * @return a list of items
     */
    public String listMessage(String input) {
        return ("Take a look at ye DREAM goals for 2023 \n") + input;
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
    public static void error(String code) throws TaskException {
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
