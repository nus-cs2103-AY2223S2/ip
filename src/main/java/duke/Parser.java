package duke;

public class Parser {
    public Integer getMarkNum(String input, boolean isMark) {
        if (isMark) {
            return Integer.valueOf(input.substring(5));
        } else {
            return Integer.valueOf(input.substring(7));
        }
    }

    public String getTodoName(String input) {
        if (input.substring(4).equals("")) {
            return "";
        }
        return input.substring(5);
    }

    public String getDeadlineDl(String input) {
        if (input.indexOf("/by") == -1) {
            return "";
        }
        return input.substring(input.indexOf("/by") + 4);
    }

    public String getDeadlineName(String input) {
        if (input.indexOf("/by") == 9) {
            return "";
        }
        return input.substring(9, input.indexOf("/by") - 1);
    }

    public String getEventStart(String input) {
        int start = input.indexOf("/from");
        int end = input.indexOf("/to");
        if (start == -1 || end == -1) {
            return "";
        }
        return input.substring(start + 6, end - 1);
    }

    public String getEventEnd(String input) {
        return input.substring(input.indexOf("/to") + 4);
    }

    public String getEventName(String input) {
        int start = input.indexOf("/from");
        if (start == 6) {
            return "";
        }
        return input.substring(6, start - 1);
    }

    public Integer getDeleteNum(String input) {
        return Integer.valueOf(input.substring(7));
    }

    public String getKeyword(String input) {
        return input.substring(5);
    }
}
