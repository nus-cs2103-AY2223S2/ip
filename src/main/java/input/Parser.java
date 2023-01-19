package input;

import exception.DukeException;

public class Parser {
    
    // regex: ^deadline\\s.+/by\\s.+$
    public static String[] parseDeadline(String input) throws DukeException {
        try {
            String removeDeadlineKeyword = input.substring(9);
            int by = removeDeadlineKeyword.indexOf("/by");
            String task = removeDeadlineKeyword.substring(0, by).strip();
            String deadline = removeDeadlineKeyword.substring(by+3, removeDeadlineKeyword.length()).strip();
            return new String[] {task, deadline};
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unexpected input format, could not parse deadline properly.");
        } catch (Exception e) {
            throw new DukeException("Unexpected error occured");
        }
    }

    // regex: ^event\\s.+/from\\s.+/to\\s.+$
    public static String[] parseEvent(String input) throws DukeException {
        try {
            String removeEventKeyword = input.substring(6);
            int from = removeEventKeyword.indexOf("/from");
            int to = removeEventKeyword.indexOf("/to");
            String task = removeEventKeyword.substring(0, from).strip();
            String start = removeEventKeyword.substring(from+5, to).strip();
            String end = removeEventKeyword.substring(to+3, removeEventKeyword.length()).strip();
            return new String[] {task, start, end};
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unexpected input format, could not parse event properly.");
        } catch (Exception e) {
            throw new DukeException("Unexpected error occured");
        }
    }

    // regex: ^todo\\s.+$
    public static String parseTodo(String input) throws DukeException {
        try {
            return input.substring(5, input.length()).strip();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unexpected input format, could not parse todo properly.");
        } catch (Exception e) {
            throw new DukeException("Unexpected error occured");
        }
    }

    public static int parseMark(String input) throws DukeException {
        try {
            int markIndex = Integer.valueOf(input.substring(5)) - 1; // account for 1 indexing
            return markIndex;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unexpected input format, could not parse mark properly.");
        } catch (Exception e) {
            throw new DukeException("Unexpected error occured");
        }
    }

    public static int parseUnmark(String input) throws DukeException {
        try {
            int unmarkIndex = Integer.valueOf(input.substring(7)) - 1; // account for 1 indexing
            return unmarkIndex;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Unexpected input format, could not parse unmark properly.");
        } catch (Exception e) {
            throw new DukeException("Unexpected error occured");
        }
    }
}
