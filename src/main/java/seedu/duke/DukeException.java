package seedu.duke;

public class DukeException extends Exception{
    public DukeException(){
        super("    -------------------------------------------\n    " +
                "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n    " +
                "-------------------------------------------");
    }
}
