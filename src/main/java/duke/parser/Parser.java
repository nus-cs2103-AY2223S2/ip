package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

import duke.Commands;
import duke.command.AddTaskCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.MarkDoneCommand;
import duke.command.MarkUndoneCommand;
import duke.command.ShowListCommand;
import duke.command.UndoCommand;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;


public class Parser {


    /**
     * Parses the user's instructions and creates commands
     *
     * @param input The input from the user
     * @return A command object corresponding to the user's command
     * @throws DukeException            Throws Duke Exception that contains a specific error message
     * @throws IllegalArgumentException Throws an exception when the input command does not exist
     */
    public static Command parse(String input, Command preCommand) throws DukeException, IllegalArgumentException {
        String command = input.split(" ")[0].toUpperCase();
        Commands cur = Enum.valueOf(Commands.class, command);
        switch (cur) {
        case LIST:
            return new ShowListCommand();
        case MARK:
            int index = Integer.valueOf(input.split(" ")[1]);
            return new MarkDoneCommand(index - 1);
        case UNMARK:
            index = Integer.valueOf(input.split(" ")[1]);
            return new MarkUndoneCommand(index - 1);
        case DEADLINE:
            try {
                String detail = input.split("/")[0].split(" ", 2)[1];
                //parse the input to extract the deadline time

                String timeDescription =
                    input.split("/")[1].split(" ")[0] + ": "
                        + input.split("/")[1].split(" ", 2)[1];
                LocalDate time = LocalDate.parse(timeDescription.split(": ", 2)[1]);
                return new AddTaskCommand(new Deadline(detail, time));
            } catch (ArrayIndexOutOfBoundsException | PatternSyntaxException | DateTimeParseException e) {
                throw new DukeException("   OOPS!!! Your input format is wrong.\n"
                    + " Try deadline do homework /by 2023-01-02");
            }
        case EVENT:
            try {
                String detail = input.split("/")[0].split(" ", 2)[1];
                //parse the input to extract the duration of the event
                String timeDescription = input.split("/")[1].split(" ")[0] + " "
                    + input.split("/")[1].split(" ", 2)[1]
                    + input.split("/")[2].split(" ")[0] + " "
                    + input.split("/")[2].split(" ", 2)[1];
                return new AddTaskCommand(new Event(detail, timeDescription));
            } catch (ArrayIndexOutOfBoundsException | PatternSyntaxException e) {
                throw new DukeException("   OOPS!!! Your input format is wrong.\n"
                    + " Try event meeting /from (anytime) /to (anytime)");
            }
        case TODO:
            if (input.split(" ").length == 1) {
                throw new DukeException("   OOPS!!! The description of a todo cannot be empty.\n");
            }
            String detail = input.split(" ", 2)[1];
            return new AddTaskCommand(new Todo(detail));
        case DELETE:
            index = Integer.valueOf(input.split(" ")[1]);
            return new DeleteCommand(index - 1);
        case BYE:
            return new ByeCommand();
        case FIND:
            detail = input.split(" ", 2)[1].trim();
            return new FindCommand(detail);
        case UNDO:
            return new UndoCommand(preCommand);
        default:
            throw new DukeException(
                "   OOPS!!! I'm sorry, but I don't know what that means :-(\n"
            );
        }
    }
}










