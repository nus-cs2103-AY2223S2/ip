package chad.backend;

import chad.commands.*;
import chad.tasks.Task;

public class Parser {
    private final TaskList tasklist;

    /**
     * A flag to indicate that Chad is handling duplicates.
     */
    private boolean isCheckingDuplicates = false;

    /**
     * Stores the duplicate item that is being stored.
     */
    private Task duplicateTask;

    public Parser(TaskList tasklist) {
        this.tasklist = tasklist;
    }

    public String parse(String instr) {
        String[] tokens = instr.strip().toLowerCase().split(" ", 2);
        String command = tokens[0];
        Command c;

        //  Guard Clause for duplicate checking.
        if (isCheckingDuplicates) {
            switch (command) {
            case "yes":
                c = new ForceAddDuplicate(duplicateTask, tasklist);
                this.isCheckingDuplicates = false;
                return c.execute();

            case "no":
                this.isCheckingDuplicates = false;
                return "OK, I'm not adding this duplicate task.";

            default:
                this.isCheckingDuplicates = false;
            }
        }

        switch (command) {
        case "welcome":
            return "Hello, Boss. I am Chad, your personal assistant.";
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
            assert tokens.length > 1;
            String description = tokens[1];
            c = new MakeTodo(description, tasklist, this);
            break;

        case "deadline":
            assert tokens.length > 1;
            String[] deadlineFinder = tokens[1].split(" /by ");
            assert deadlineFinder.length == 2;
            description = deadlineFinder[0];
            String by = deadlineFinder[1];
            c = new MakeDeadline(description, by, tasklist, this);
            break;

        case "event":
            String[] fromFinder = tokens[1].split(" /from ");
            description = fromFinder[0];
            String[] toFinder = fromFinder[1].split(" /to ");
            assert toFinder.length == 2;
            String from = toFinder[0];
            String to = toFinder[1];
            c = new MakeEvent(description, from, to, tasklist, this);
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

    public void handleDuplicates(Task t) {
        this.isCheckingDuplicates = true;
        this.duplicateTask = t;
    }
}
