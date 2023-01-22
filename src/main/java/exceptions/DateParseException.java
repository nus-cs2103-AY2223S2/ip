package exceptions;

public class DateParseException extends DukeException {
    public DateParseException(String msg) {
        super("☹ OOPS!!! Invalid Date format! " +  msg );
    }
}