package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputValidator {
    private static final String[] RESERVED = new String[]{"/by", "/to", "/from"};

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

    public static String[] normaliseTodoInput(String input) throws InvalidCommandException {
        String[] split = input.split(" ");
        List<String> RESERVED_AL = Arrays.asList(RESERVED);
        for (String s : split) {
            if (RESERVED_AL.contains(s)) {
                throw new InvalidCommandException("There should not be any reserved keywords");
            }
        }

        String[] res = new String[2];
        try {
            res[0] = input.substring(0, 4);
            res[1] = input.substring(5);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Unable to normalise input as a Todo input");
        }

        return res;
    }

    public static String[] normaliseDeadlineInput(String input) throws
            IndexOutOfBoundsException, InvalidCommandException, NoDeadlineFoundException {
        String[] split = input.split(" ");
        if (split.length < 4) {
            throw new InvalidCommandException("Invalid Syntax - \"deadline [title] /by [deadline]\" (e.g. \"deadline physics project /by tomorrow 3pm\"");
        }
        int totalKeywords = 0;
        List<String> RESERVED_AL = Arrays.asList(RESERVED);
        ArrayList<String> keywords = new ArrayList<>();
        for (String s : split) {
            if (RESERVED_AL.contains(s)) {
                totalKeywords++;
                keywords.add(s);
            }
        }

        if (totalKeywords == 0) {
            throw new InvalidCommandException("Missing /by keyword in input!");
        } else if (totalKeywords > 1) {
            throw new InvalidCommandException("Too many keywords in input!");
        } else if (!keywords.get(0).equals("/by")) {
            throw new InvalidCommandException("Missing /by keyword in input!");
        }

        if (split[1].equals("/by")) {
            throw new InvalidCommandException("Empty title");
        }

        String[] res = new String[3];
        res[0] = input.substring(0, 8);
        res[2] = extractDeadline(input);
        res[1] = input.substring(9, input.indexOf("/by ") - 1);
        return res;
    }

    public static String[] normaliseEventInput(String input) throws
            IndexOutOfBoundsException, InvalidCommandException,
            NoStartDateTimeFoundException, NoEndDateTimeFoundException {
        String[] split = input.split(" ");
        if (split.length < 6) {
            throw new InvalidCommandException("Invalid syntax");
        }
        int totalKeywords = 0;
        List<String> RESERVED_AL = Arrays.asList(RESERVED);
        ArrayList<String> keywords = new ArrayList<>();
        String firstKeyword = "";
        for (String s : split) {
            if (RESERVED_AL.contains(s)) {
                totalKeywords++;
                keywords.add(s);

                if (firstKeyword.isEmpty()) {
                    firstKeyword = s;
                }
            }
        }

        if (totalKeywords != 2 || !(keywords.contains("/from") && keywords.contains("/to"))) {
            throw new InvalidCommandException("There should only have /from and /to keyword");
        }

        if (split[1].equals("/from") || split[1].equals("/to")) {
            throw new InvalidCommandException("Empty title");
        }

        String[] res = new String[4];
        res[0] = input.substring(0, 5);

        res[1] = input.substring(6, input.indexOf(firstKeyword + " ") - 1);
        res[2] = extractStartDateTime(input);
        res[3] = extractEndDateTime(input);
        return res;
    }

    public static String extractDeadline(String input) throws NoDeadlineFoundException {
        int deadlineStartIndex = input.indexOf("/by ");

        if (deadlineStartIndex == -1) {
            throw new NoDeadlineFoundException("Unable to find \"/by\" subcommand");
        }

        return input.substring(deadlineStartIndex + 4);
    }

    public static String extractStartDateTime(String input) throws NoStartDateTimeFoundException {
        int dateTimeStartIndex = input.indexOf("/from ");
        if (dateTimeStartIndex == -1) {
            throw new NoStartDateTimeFoundException("Unable to find \"/from\" subcommand");
        }

        String substring = input.substring(dateTimeStartIndex);
        if (substring.split(" ")[1].startsWith("/") || substring.split(" ")[1].isEmpty()) {
            throw new NoStartDateTimeFoundException("Unable to find start dateTime");
        }

        int dateTimeEndIndex = substring.indexOf(" /");
        if (dateTimeEndIndex == -1) {
            return input.substring(dateTimeStartIndex + 6);
        } else {
            return input.substring(dateTimeStartIndex + 6, dateTimeEndIndex + dateTimeStartIndex);
        }
    }

    public static String extractEndDateTime(String input) throws NoEndDateTimeFoundException {
        int dateTimeStartIndex = input.indexOf("/to ");
        if (dateTimeStartIndex == -1) {
            throw new NoEndDateTimeFoundException("Unable to find \"/to\" subcommand");
        }

        String substring = input.substring(dateTimeStartIndex);
        if (substring.split(" ")[1].startsWith("/") || substring.split(" ")[1].isEmpty()) {
            throw new NoEndDateTimeFoundException("Unable to find end dateTime");
        }

        int dateTimeEndIndex = substring.indexOf(" /");
        if (dateTimeEndIndex == -1) {
            return input.substring(dateTimeStartIndex + 4);
        } else {
            return input.substring(dateTimeStartIndex + 4, dateTimeEndIndex + dateTimeStartIndex);
        }
    }
}
