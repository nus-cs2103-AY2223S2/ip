import java.util.Arrays;

public class Parser {
    public int getMarkNum(String input, boolean isMark) {
        if(isMark) {
            return Integer.valueOf(input.substring(5));
        } else {
            return Integer.valueOf(input.substring(7));
        }
    }

    public String getTodoDescription(String input) {
        if (input.substring(4).equals("")) {
            return "";
        }
        return input.substring(5);
    }

    public String getDeadlineby(String input) {
        if (input.indexOf("/by") == -1) {
            return "";
        }
        return input.substring(input.indexOf("/by") + 4);
    }

    public String getDeadlineDescription(String input) {
        if (input.indexOf("/by") == 9) {
            return "";
        }
        return input.substring(9, input.indexOf("/by") - 1);
    }

    public String getEventDescription(String input) {
        String[] arr = input.split(" ");
        if (arr.length == 1) {
            return "";
        } else {
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] == "/from") {
                    return String.join(" ", Arrays.copyOfRange(arr, 1, i+1));
                }
            }
            return "";
        }
    }

    public String getEventFrom(String input) {
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
}
