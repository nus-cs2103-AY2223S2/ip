package duke.parser;

import duke.command.*;

public class Parser {
    private enum Cmdtype {
        todo,
        deadline,
        mark,
        unmark,
        list,
        bye,
        delete,
        find,
        event;
    }

    public static Command parse(String cmd) throws IllegalArgumentException {
        String[] cmd2 = cmd.split(" ");
        Cmdtype cmdtype = Cmdtype.valueOf(cmd2[0].toLowerCase());
        switch (cmdtype) {
            case todo:
                return new TodoCommand(cmd);
            case deadline:
                return new DeadlineCommand(cmd);
            case mark:
                return new MarkCommand(cmd2[1]);
            case unmark:
                return new UnmarkCommand(cmd2[1]);
            case list:
                return new ListCommand(cmd);
            case bye:
                return new ByeCommand();
            case delete:
                return new DeleteCommand(cmd2[1]);
            case event:
                return new EventCommand(cmd);
            default:
                throw new IllegalArgumentException();
        }
    }
}