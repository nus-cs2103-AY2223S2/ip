package DukeBot.Exception.BlankFieldExceptions;

public class BlankFieldEventException extends BlankFieldException {
    public BlankFieldEventException() {
        super("\n" + "    ____________________________________________________________\n" + "\n" +
                "     ☹ OOPS!!! The description, start date or end date of a event cannot be empty." + "\n" +
                "    ____________________________________________________________\n");
    }
}
