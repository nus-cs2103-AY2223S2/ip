package userinteraction;

import command.*;

public class Parser {

    private static final Ui ui = new Ui();

    public static Command parse(String input) {
        if (input.equals("bye")) {
            ui.printLine();
            return new ExitCommand(input);
        } else if (input.equals("list")) {
            ui.printLine();
            return new ListCommand(input);
        } else if (input.startsWith("mark")) {
            ui.printLine();
            return new MarkTaskCommand(input, true);
        } else if (input.startsWith("unmark")) {
            ui.printLine();
            return new MarkTaskCommand(input, false);
        } else if (input.startsWith("todo")) {
            ui.printLine();
            return new AddTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            ui.printLine();
            return new AddDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            ui.printLine();
            return new AddEventCommand(input);
        } else if (input.startsWith("delete")) {
            ui.printLine();
            return new DeleteTaskCommand(input);
        } else if (input.startsWith("find")) {
            ui.printLine();
            return new FindTaskCommand(input);
        } else {
            ui.printLine();
            System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means.\n");
        }
        return null;
    }
}
