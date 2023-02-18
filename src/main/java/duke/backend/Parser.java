package duke.backend;

import duke.commands.Bye;
import duke.commands.Command;
import duke.commands.Delete;
import duke.commands.Find;
import duke.commands.List;
import duke.commands.MakeDeadline;
import duke.commands.MakeEvent;
import duke.commands.MakeTodo;
import duke.commands.Mark;
import duke.commands.Unmark;

class Parser {
    public static final String DIVIDER = "____________________________________________________________\n";
    private final TaskList tasklist;

    public Parser(TaskList tasklist) {
        this.tasklist = tasklist;
    }

    public String parse(String instr) {
        String[] tokens = instr.split(" ", 2);
        String command = tokens[0];
        Command c;
        switch (command) {
        case "list":
            c = new List(tasklist);
            break;

        case "bye":
            c = new Bye(tasklist);
            break;

        case "mark":
            int idx = Integer.parseInt(tokens[1]);
            c = new Mark(idx, tasklist);
            break;

        case "unmark":
            idx = Integer.parseInt(tokens[1]);
            c = new Unmark(idx, tasklist);
            break;

        case "todo":
            String description = tokens[1];
            c = new MakeTodo(description, tasklist);
            break;

        case "deadline":
            String[] moreTokens = tokens[1].split(" /by ");
            description = moreTokens[0];
            String by = moreTokens[1];
            c = new MakeDeadline(description, by, tasklist);
            break;

        case "event":
            String[] fromFinder = tokens[1].split(" /from ");
            description = fromFinder[0];
            String[] toFinder = fromFinder[1].split(" /to ");
            String from = toFinder[0];
            String to = toFinder[1];
            c = new MakeEvent(description, from, to, tasklist);
            break;

        case "delete":
            idx = Integer.parseInt(tokens[1]);
            c = new Delete(idx, tasklist);
            break;

        case "find":
            String searchKey = tokens[1];
            c = new Find(searchKey, tasklist);
            break;

        default:
            return "OOPS! I'm sorry, but I don't know what that means :-(\n";
        }

        return c.execute();
    }
}
