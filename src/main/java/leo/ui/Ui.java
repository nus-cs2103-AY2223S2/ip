package leo.ui;

import leo.storage.Task;

/**
 * Customise the user interface of Leo.
 */
public class Ui {

    /**
     * Prints Welcome Logo and greeting messages.
     *
     */
    public void greetings() {
        String helloLogo = "__   __   ______   __       __        _____ \n"
                + "| |  | |  | ____|  | |      | |      /  __  \\ \n"
                + "| |__| |  | |____  | |      | |      | |  | |\n"
                + "|  __  |  | ____|  | |      | |      | |  | |\n"
                + "| |  | |  | |____  | |____  | |____  | |__| |\n"
                + "|_|  |_|  |______| |______| |______| \\_____/\n";
        displayMessage(helloLogo);
        String greetingMessage = "Good day, I am Leo!";
        String followUp = "How can I assist you today?";
        displayMessage(leoResponse(greetingMessage));
        displayMessage(notFirstLine(followUp));
    }

    /**
     * Prints the String.
     *
     * @param str String to be printed.
     */
    public static void displayMessage(String str) {
        System.out.println(str);
    }

    /**
     * Returns formatted String structure of response from Leo.
     *
     * @param str String to be formatted.
     * @return Formatted String response.
     */
    public static String leoResponse(String str) {
        String response = "Leo: ";
        return response + str;
    }

    /**
     * Returns formatted String structure of second line response from Leo.
     *
     * @param str String to be formatted.
     * @return Formatted String response.
     */
    public static String notFirstLine(String str) {
        String spaces = "     ";
        return spaces + str;
    }
    /**
     * Returns String representation of TaskType.
     *
     * @param t Task to convert TaskType from.
     * @return String representation of TaskType.
     */
    public static String type(Task t) {
        switch (t.getType()) {
        case TODO:
            return "[T]";
        case DEADLINE:
            return "[D]";
        case EVENT:
            return "[E]";
        default:
            return null;
        }
    }

    /**
     * Returns String representation of status of Task.
     *
     * @param t Task to retrieve completion status from.
     * @return String representation of done status.
     */
    public static String completion(Task t) {
        return (t.isDone() ? "[X] " : "[ ] ");
    }

}
