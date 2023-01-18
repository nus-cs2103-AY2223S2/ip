package input;

public class Parser {
    
    // regex: ^deadline\\s.+/by\\s.+$
    public static String[] parseDeadline(String input) {
        String removeDeadlineKeyword = input.substring(9);
        int by = removeDeadlineKeyword.indexOf("/by");
        String task = removeDeadlineKeyword.substring(0, by).strip();
        String deadline = removeDeadlineKeyword.substring(by+3, removeDeadlineKeyword.length()).strip();
        return new String[] {task, deadline};
    }

    // regex: ^event\\s.+/from\\s.+/to\\s.+$
    public static String[] parseEvent(String input) {
        String removeEventKeyword = input.substring(6);
        int from = removeEventKeyword.indexOf("/from");
        int to = removeEventKeyword.indexOf("/to");
        String task = removeEventKeyword.substring(0, from).strip();
        String start = removeEventKeyword.substring(from+5, to).strip();
        String end = removeEventKeyword.substring(to+3, removeEventKeyword.length()).strip();
        return new String[] {task, start, end};
    }

    // regex: ^todo\\s.+$
    public static String parseTodo(String input) {
        return input.substring(5, input.length()).strip();
    }
}
