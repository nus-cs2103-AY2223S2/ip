package duke.helpers;

public class Ui {

    /**
     * Returns lateral location of the specified position.
     * If the position is unset, NaN is returned.
     *
     * @param str String to be formatted, which will be shown in terminal.
     */
    public static String formatStr(String str) {
        String returnstr =  ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
                + str + "\n"
                + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
        return returnstr;
    }

    /**
     * Prints greeting message upon startup.
     */
    public static void doGreeting() {
        String greeting = formatStr("Hello! I'm Muse!\n"
                + "What can I do for you?");
        System.out.println(greeting);
    }

    /**
     * Prints farewell message before program terminates.
     */
    public static void doFarewell() {
        String goodbyeMessage = formatStr("Bye. Come back again!");
        System.out.println(goodbyeMessage);
    }

    /**
     * Prints the found results, also using formatStr.
     *
     * @param hasFound Indicates whether a search result has been found or not.
     * @param outputResult Contains the entire row by row result of results.
     */
    public static void outputSearchResults(boolean hasFound, String outputResult) {
        String returnStr;
        if (hasFound) {
            returnStr = "Gotcha. We found the following terms: \n" + outputResult;
        } else {
            returnStr = "Sorry... we couldn't find any results matching :(";
        } System.out.println(formatStr(returnStr));
    }

}
