package utils;

public class InputValidator {
    public static boolean isCheckInputValid(String input) {
        String[] split = input.split(" ");

        if (split.length != 2) {
            return false;
        }

        try {
            Integer.parseInt(split[1]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
