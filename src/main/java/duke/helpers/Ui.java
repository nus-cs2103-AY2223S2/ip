package duke.helpers;

public class Ui {

    /**
     * Returns lateral location of the specified position.
     * If the position is unset, NaN is returned.
     *
     * @param str String to be formatted, which will be shown in terminal.
     */
    public static String formatStr(String str) {
        String returnstr =  "..................................\n"
                + str + "\n"
                + "..................................";
        return returnstr;
    }

    /**
     * Prints greeting message upon startup.
     */
    public static String doGreeting() {
        String greeting = "Hello! I'm Muse!\n"
                + "What can I do for you?\n" +
                "enter \"help\" to see our help-sheet!\n";
        return greeting;
    }

    /**
     * Prints farewell message before program terminates.
     */
    public static String doFarewell() {
        String goodbyeMessage = "Bye. Come back again!";
        return goodbyeMessage;
    }

    /**
     * Prints the found results, also using formatStr.
     *
     * @param hasFound Indicates whether a search result has been found or not.
     * @param outputResult Contains the entire row by row result of results.
     */
    public static String outputSearchResults(boolean hasFound, String outputResult) {
        String returnStr;
        if (hasFound) {
            returnStr = "Gotcha. We found the following terms: \n" + outputResult;
        } else {
            returnStr = "Sorry... we couldn't find any results matching :(";
        }
        return returnStr;
    }

    public static String generateHelpSheet() {
        String helpMessage = "";
        helpMessage += "Welcome to Muse: the Chatbot that organizes your tasks!\n";
        helpMessage += "Here are the common instructions we use: \n";
        helpMessage += "todo xxx - this enters a Todo task, where xxx will be the task name.\n";
        helpMessage += "deadline xxx /by yyy - this enters a deadline task, where xxx will be the task name." +
                "yyy will be the deadline timing.\n";
        helpMessage += "deadline xxx /by dd/mm/yyyy TTTT (time in 24h)" +
                " - entering deadlines in this style registers the actual date!\n";
        helpMessage += "event xxx /from yyy /to zzz - This enters a event task, with yyy and zzz being the start" +
                "and end times of the event. They can be in dd/mm/yyyy TTTT form too. \n";
        helpMessage += "\n----------------USEFUL COMMANDS-------------------\n\n";
        helpMessage += "find xxxx - Muse will find entry/entries with the word xxxx inside!\n";
        helpMessage += "delete x - Muse will delete the entry with the entry number x!\n";
        helpMessage += "mark x - Muse will mark the entry with the entry number x as DONE!\n";
        helpMessage += "unmark x - Muse will mark the entry with the entry number x as UNDONE.\n";
        helpMessage += "list - Muse will give you a comprehensive list of tasks that you have at the moment!\n";
        helpMessage += "bye - this ends Muse, as the current list of tasks and their states are saved!\n";
        helpMessage += "now, go and give Muse a try! make your life easier!! >:D";
        return helpMessage;
    }

}
