package nook;

/**
 * Represents the Ui that handles all interactions with the user
 */
public class Ui {

    /**
     * Formats the input string to standard output message format for duke CLI.
     *
     * @param input The string to be formatted.
     * @return The formatted string.
     */
    public static String formatOutput(String input) {
        String line = "____________________________________________________________\n";
        return line + input + "\n" + line;
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
     * Retrieves the greeting message.
     *
     * @return The greeting message string.
     */
    public String getGreeting() {
        return "Hello there, yes yes! I'm Tom Nook\nHow can I assist you today? :)";
    }

    /**
     * Prints the farewell message.
     *
     * @return The farewell message string.
     */
    public String getBye() {
        return "Have a great day, don't forget to stay on top of those tasks!";
    }

    /**
     * Retrieves the error message of the given exception.
     *
     * @param e The exception that was thrown.
     * @return The exception message string.
     */
    public String getExceptionError(Exception e) {
        return e.getMessage();
    }

    /**
     * Retrieves the error message for when there is an error parsing a date-time string.
     *
     * @return The exception message string.
     */
    public String getDateTimeParseError() {
        return "Woah.. Error parsing date time for deadline task. "
              + "Please enter the by datetime in this format yyyy-mm-dd (e.g., 2023-10-15).";
    }

    /**
     * Retrieves the error message for when there is an error loading data.
     *
     * @return The exception message string.
     */
    public String getLoadingError() {
        return "No initial data found. I'll be generating a new task list for you..";
    }

    /**
     * Retrieves the error message for when there is an error saving data.
     *
     * @return The exception message string.
     */
    public String getSavingError() {
        return "Uh oh... An error occurred while I was trying to save your latest task list :0";
    }

    /**
     * Shows the greeting message to the console.
     */
    public void showGreeting() {
        printOutput(getGreeting());
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
}
