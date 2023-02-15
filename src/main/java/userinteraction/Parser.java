package userinteraction;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.DeleteTaskCommand;
import command.ExitCommand;
import command.FindTaskCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkTaskCommand;


public class Parser {

    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new ExitCommand(input);
        } else if (input.equals("list")) {
            return new ListCommand(input);
        } else if (input.startsWith("mark")) {
            return new MarkTaskCommand(input, true);
        } else if (input.startsWith("unmark")) {
            return new MarkTaskCommand(input, false);
        } else if (input.startsWith("todo")) {
            return new AddTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new AddDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new AddEventCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteTaskCommand(input);
        } else if (input.startsWith("find")) {
            return new FindTaskCommand(input);
        } else {
            return new InvalidCommand(input);
        }
    }
}
