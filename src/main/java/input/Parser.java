package input;

public class Parser {
    
    // regex: ^deadline\\s.+/by\\s.+$
    public String[] parseDeadline(String input) {
        String removeDeadlineKeyword = input.substring(9);
        int by = input.indexOf("/by");
        String task = removeDeadlineKeyword.substring(0, by).strip();
        String deadline = removeDeadlineKeyword.substring(by+3, removeDeadlineKeyword.length()).strip();
        return new String[] {task, deadline};
    }

    // regex: ^event\\s.+/from\\s.+/to\\s.+$
    public String[] parseEvent(String input) {
        String removeEventKeyword = input.substring(6);
        int from = input.indexOf("/from");
        int to = input.indexOf("/to");
        String task = removeEventKeyword.substring(0, from).strip();
        String start = removeEventKeyword.substring(from+5, to).strip();
        String end = removeEventKeyword.substring(to, removeEventKeyword.length());
        return new String[] {task, start, end};
    }

    // regex: ^todo\\s.+$
    public String parseTodo(String input) {
        return input.substring(5, input.length()).strip()
    }
}
