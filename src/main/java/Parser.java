import java.io.IOException;
import java.util.InputMismatchException;

public class Parser {

    void parse() throws DukeException{
        // switch case for future commands
        switch (Duke.userScan.next()) {
            // loop breaks, ending program if input is "bye"
            case ("bye"):
                new CommandBye().handle();
                break;

            // Duke lists out all Task names in TaskList when input is "list"
            case ("list"):
                new CommandList().handle();
                break;

            // Duke allows user to mark tasks as done when input is "mark"
            case ("mark"):
                new CommandMark().handle();
                break;

            // Duke allows user to mark tasks as NOT done when input is "unmark"
            case ("unmark"):
                new CommandUnmark().handle();
                break;

            // Duke deletes task when input is "delete"
            case ("delete"):
                new CommandDelete().handle();
                break;

            // Duke adds Deadline
            case ("deadline"):
                new CommandDeadline().handle();
                break;

            // Duke adds Event
            case ("event"):
                new CommandEvent().handle();
                break;

            // Duke adds ToDo
            case ("todo"):
                new CommandToDo().handle();
                break;

            // Duke does not understand any other commands (yet).
            default:
                Duke.ui.print("Yeah, i'm sorry. I don't understand that.");
        }
    }
}
