package util;

/**
 * A library of useful methods to parse various String
 * user inputs and retrieve data from it.
 */
public class Parser {

    /**
     * Removes leading and trailing whitespaces in user
     * input and extracts the command.
     *
     * @param input
     * @return command String
     */
    public String getCommandFromInput(String input) {
        //remove leading and trailing whitespaces
        String ip = input.trim();
        if(!ip.isBlank()) {
            String[] inputArr = ip.split(" ", 2);
            return inputArr[0];
        }
        System.out.println("No command given, please give me one!");
        return "error";
    }

    //credit: https://stackabuse.com/java-check-if-string-is-a-number/

    /**
     * Checks if a given String is numeric.
     *
     * @param string
     * @return true if String is numeric and false otherwise
     */
    public static boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Illegal string input!");
        }
        return false;
    }
}
