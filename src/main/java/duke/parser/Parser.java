package duke.parser;

import duke.command.AddTaskCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.ShowListCommand;
import duke.command.MarkDoneCommand;
import duke.command.MarkUndoneCommand;
import duke.Commands;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.command.DeleteCommand;

import java.time.LocalDate;

public class Parser {


    /**
     * Parses the user's instructions and creates commands
     *
     * @param input The input from the user
     * @return A command object corresponding to the user's command
     * @throws DukeException
     * @throws IllegalArgumentException
     */
    public static Command parse(String input) throws DukeException, IllegalArgumentException {
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
                    String detail = input.split("/")[0].split(" ", 2)[1];
                    String timeDescription = input.split("/")[1].split(" ")[0] + ": " + input.split("/")[1].split(" ", 2)[1];
                    LocalDate time = LocalDate.parse(timeDescription.split(": ", 2)[1]);
                    return new AddTaskCommand(new Deadline(detail, time));
                case EVENT:
                    detail = input.split("/")[0].split(" ", 2)[1];
                    timeDescription = input.split("/")[1].split(" ")[0] + " " + input.split("/")[1].split(" ", 2)[1] +
                            input.split("/")[2].split(" ")[0] + " " + input.split("/")[2].split(" ", 2)[1];
                    return new AddTaskCommand(new Event(detail, timeDescription));
                case TODO:
                    if (input.split(" ").length == 1) {
                        throw new DukeException("____________________________________________________________\n" +
                                "  ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                "____________________________________________________________");
                    }
                    detail = input.split(" ",2)[1];
                    return new AddTaskCommand(new Todo(detail));
                case DELETE:
                    index = Integer.valueOf(input.split(" ")[1]);
                    return new DeleteCommand(index - 1);
                case BYE:
                    return new ByeCommand();
                default:
                    throw new DukeException("____________________________________________________________\n" +
                            "  ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                            "____________________________________________________________");
            }

    }
}










