package wessy;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import wessy.exceptions.integer.NotPositiveIntegerException;

public class Parser {
    static CmdType getCmd(String userInput) {
        int idx = userInput.indexOf(' ');
        if (idx == -1) {
            idx = userInput.length();
        }
        return CmdType.getCmdType(userInput.substring(0, idx));
    }

    // Only for Deadline, Event & ToDo commands
    static String[] getTaskComponents(String userInput, CmdType cmd) {
        String byStr = "/by";
        String fromStr = "/from";
        String toStr = "/to";
        int firstIdx;
        int secondIdx;
        String description = userInput.substring(cmd.getStrLength() + 1);

        switch (cmd) {
        case TODO:
            return new String[]{description};
        case DEADLINE:
            firstIdx = description.indexOf(byStr);
            return new String[]{removeSpacePadding(description.substring(0, firstIdx)),
                    removeSpacePadding(description.substring(firstIdx + byStr.length()))};
        case EVENT:
            firstIdx = description.indexOf(fromStr);
            secondIdx = description.indexOf(toStr);
            return new String[]{removeSpacePadding(description.substring(0, firstIdx)),
                    removeSpacePadding(description.substring(firstIdx + fromStr.length(), secondIdx)),
                    removeSpacePadding(description.substring(secondIdx + toStr.length()))};
        }

        return new String[0];
    }

    public static LocalDateTime parseDateTime(String str) throws DateTimeParseException {
        str = removeSpacePadding(str);

        if (count(str, ':') == 2) {
            return LocalDateTime.parse(str);
        }
        int n = str.length();
        if (n <= 10) {
            return LocalDateTime.parse(standardiseDateFormat(str) + "T12:34:56");
        }

        int idx = 10;
        if (str.charAt(9) == ' ') {
            idx = 9;
        } else if (str.charAt(8) == ' ') {
            idx = 8;
        }
        if (str.charAt(idx + 3) == ':') {
            return LocalDateTime.parse(standardiseDateFormat(str.substring(0, idx)) + "T"
                    + str.substring(idx + 1) + ":00");
        }
        if (str.charAt(idx + 3) == '.') {
            return LocalDateTime.parse(standardiseDateFormat(str.substring(0, idx)) + "T"
                    + str.substring(idx + 1, idx + 3) + ":" + str.substring(idx + 4) + ":00");
        }

        return LocalDateTime.parse(standardiseDateFormat(str.substring(0, idx)) + "T"
                + str.substring(idx + 1, idx + 3) + ":" + str.substring(idx + 3) + ":00");
    }

    static int count(String str, char target) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == target) {
                num++;
            }
        }
        return num;
    }

    // Used in parseDateTime
    private static String standardiseDateFormat(String str) throws DateTimeParseException {
        try {
            String[] components = str.split("-", 3);

            if (str.contains("/")) {
                components = str.split("/", 3);
            }

            for (int i = 0; i < components.length; i++) {
                if (components[i].length() == 1) {
                    components[i] = "0" + components[i];
                }
            }

            if (components[0].length() == 4) {
                return components[0] + "-" + components[1] + "-" + components[2];
            }
            return components[2] + "-" + components[1] + "-" + components[0];

        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new DateTimeParseException("", str, 0);
        }
    }

    public static int parseInt(String userInput, CmdType cmd) throws NotPositiveIntegerException {
        int num = Integer.parseInt(removeSpacePadding(userInput.substring(cmd.getStrLength())));
            if (num <= 0) {
                throw new NotPositiveIntegerException();
            }
        return num;
    }

    // HELPER FUNC
    public static String removeSpacePadding(String str) {
        int start = 0;
        while (str.charAt(start) == ' ') {
            start++;
        }

        int end = str.length() - 1;
        while (str.charAt(end) == ' ') {
            end--;
        }

        return str.substring(start, end + 1);
    }
}
