package util;

public class Parser {

    public String parseScannerInput(String input) {
        //remove leading and trailing whitespaces
        String ip = input.trim();
        if (!ip.isBlank()) {
            String[] inputArr = ip.split(" ", 2);
            return inputArr[0];
        }
        System.out.println("No command given, please give me one!");
        return "error";
    }

    //credit: https://stackabuse.com/java-check-if-string-is-a-number/
    public static boolean isNumeric(String string) {
        int intValue;

        if (string == null || string.equals("")) {
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
