package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputValidator {

    private static final String TERMINATE_KEY = "bye";
    private static final String LIST_KEY = "list";
    private static final String CHECK_KEY = "done";
    private static final String UNCHECK_KEY = "undone";
    private static final String DELETE_KEY = "delete";
    private static final String TODO_KEY = "todo";
    private static final String DEADLINE_KEY = "deadline";
    private static final String EVENT_KEY = "event";
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

    public static String[] normaliseTodoInput(String input) throws IndexOutOfBoundsException, InvalidCommandException {
        String[] split = input.split(" ");
        List<String> RESERVED_AL = Arrays.asList(RESERVED);
        for (String s : split) {
            if (RESERVED_AL.contains(s)) {
                throw new InvalidCommandException("There should not be any reserved keywords");
            }
        }

        String[] res = new String[2];
        res[0] = input.substring(0, 4);
        res[1] = input.substring(5);
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
        res[0] = input.substring(0, 9);
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
        res[0] = input.substring(0, 6);

        res[1] = input.substring(6, input.indexOf(firstKeyword + " ") - 1);
        res[2] = extractStartDateTime(input);
        res[3] = extractEndDateTime(input);
        return res;
    }

    public static Command extractCommandFromInput(String input) throws EmptyInputException, InvalidCommandException {
        if (input.isEmpty()) {
            throw new EmptyInputException("Input cannot be empty");
        }

        switch (input) {
            case TERMINATE_KEY:
                return Command.BYE;
            case LIST_KEY:
                return Command.LIST;
        }

        String[] inputArr = input.split(" ");

        switch (inputArr[0]) {
            case CHECK_KEY:
                return Command.DONE;
            case UNCHECK_KEY:
                return Command.UNDONE;
            case DELETE_KEY:
                return Command.DELETE;
            case TODO_KEY:
                return Command.TODO;
            case DEADLINE_KEY:
                return Command.DEADLINE;
            case EVENT_KEY:
                return Command.EVENT;
        }

        throw new InvalidCommandException(String.format("%s is not a valid command!", inputArr[0]));
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
