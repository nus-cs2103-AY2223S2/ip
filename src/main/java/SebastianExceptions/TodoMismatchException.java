package SebastianExceptions;

import Utilities.Utilities;

public class TodoMismatchException extends InputFormatMismatchException{
    public TodoMismatchException() {
        super(
                "Please specify a todo in the following format:" + "\n" +
                        Utilities.space() + "todo [deadline]"
        );
    }
}
