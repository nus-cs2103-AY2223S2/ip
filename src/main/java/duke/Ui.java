package duke;

/**
 * Represents the Ui that handles all interactions with the user
 */
public class Ui {

    /**
     * Formats the input string to standard output message format.
     *
     * @param input The string to be formatted.
     * @return The formatted string.
     */
    public static String formatOutput(String input) {
        String line = "\t____________________________________________________________\n";
        return line + "\t " + input + "\n" + line;
    }

    /**
     * Prints the given output to the console.
     *
     * @param output The output to be printed.
     */
    public void printOutput(String output) {
        System.out.println(formatOutput(output));
    }

    /**
     * Prints the greeting message to the console.
     */
    public void showGreeting() {
        printOutput("Hey there! I'm Sirius\n\t What can I do for you today? :D");
    }

    /**
     * Prints the farewell message to the console.
     */
    public void showBye() {
        printOutput("Well, I'm off! Hope to see you again soon :)");
    }

    /**
     * Prints the error message of the given exception to the console.
     *
     * @param e The exception that was thrown.
     */
    public void showExceptionError(Exception e) {
        printOutput(e.getMessage());
    }

    /**
     * Prints the error message for when there is an error parsing a date-time string.
     */
    public void showDateTimeParseError() {
        printOutput("Woah.. Error parsing date time for deadline task. "
              + "Please enter the by datetime in this format yyyy-mm-dd (e.g., 2023-10-15).");
    }

    /**
     * Prints the error message for when there is an error loading data.
     */
    public void showLoadingError() {
        printOutput("No initial data found. I'll be generating a new task list for you..");
    }

    /**
     * Prints the error message for when there is an error saving data.
     */
    public void showSavingError() {
        printOutput("Uh oh... An error occurred while I was trying to save your latest task list :0");
    }
}
