package main.java.duke.command;

public class Parser {

    public Parser() {
    }

    public Command parse(String echo) {
        String[] command = echo.split(" ");
        switch (command[0]) {
        case "bye":
            return new ByeCommand(command);
        case "list":
            return new ListCommand(command);
        case "mark":
            return new MarkCommand(command);
        case "unmark":
            return new UnmarkCommand(command);
        case "todo":
            return new ToDoCommand(command);
        case "event":
            return new EventCommand(command);
        case "deadline":
            return new DeadlineCommand(command);
        case "delete":
            return new DeleteCommand(command);
        default:
            return new NoCommand(command);
        }
    }
}
