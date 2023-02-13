package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;

public class Parser {
    enum ActionP {
        bye, list, unmark, mark, todo, deadline, event, delete, find;

    }

    public static Command parse(String fullCommand) throws DukeException {
        String[] s = fullCommand.split(" ");
        String command = s[0];
        if (command.equals(ActionP.list.name())) {
            return new ListCommand(fullCommand);
        } else if (command.equals(ActionP.bye.name())) {
            return new ExitCommand();
        } else if (command.equals(ActionP.unmark.name()) || command.equals(ActionP.mark.name())) {
            int taskNumber = Integer.parseInt(s[1]);
            return new MarkCommand(fullCommand, command, taskNumber);
        } else if (command.equals(ActionP.todo.name()) || command.equals(ActionP.deadline.name())
                || command.equals(ActionP.event.name())) {
            return new AddCommand(fullCommand, s);
        } else if (command.equals(ActionP.delete.name())) {
            int taskNumberDelete = Integer.parseInt(s[1]);
            return new DeleteCommand(taskNumberDelete);
        } else if (command.equals((ActionP.find.name()))) {
            return new FindCommand(s);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }
}
