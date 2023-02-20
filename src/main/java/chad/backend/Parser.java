package chad.backend;

import chad.commands.*;
import chad.tasks.Task;

import java.time.format.DateTimeParseException;

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
            try {
                int idx = Integer.parseInt(tokens[1]);
                c = new Mark(idx, tasklist);

                //  Executing here to catch IndexOutOfBoundsException.
                c.execute();
                break;
            } catch (NumberFormatException nfe) {
                return "Boss, you gotta enter an index after \"mark\"";
            } catch (IndexOutOfBoundsException ioobe) {
                return "Boss, please enter a valid index.";
            }

        case "unmark":
            try {
                int idx = Integer.parseInt(tokens[1]);
                c = new Unmark(idx, tasklist);

                //  Executing here to catch IndexOutOfBoundsException.
                return c.execute();
            } catch (NumberFormatException nfe) {
                return "Boss, you gotta enter an index after \"unmark\"";
            } catch (IndexOutOfBoundsException ioobe) {
                return "Boss, please enter a valid index.";
            }

        case "todo":
            assert tokens.length > 1;
            try {
                String description = tokens[1];
                c = new MakeTodo(description, tasklist, this);
                break;
            } catch (IndexOutOfBoundsException ioobe) {
                return "Boss, please give me a name for this todo.";
            }

        case "deadline":
            assert tokens.length > 1;

            String[] tokensNameAndDeadline;
            try {
                tokensNameAndDeadline = tokens[1].split(" /by ");
                assert tokensNameAndDeadline.length == 2;
                String description = tokensNameAndDeadline[0];
                System.out.println("\n\n\nDeadline name is:" + description + "\n\n\n");
                String by = tokensNameAndDeadline[1];

                c = new MakeDeadline(description, by, tasklist, this);
                //  Execute command here to catch dtpe.
                return c.execute();
            } catch (IndexOutOfBoundsException ioobe) {
                return "Boss, please give me a deadline in the format:" +
                        "\n\n\"deadline [name] /by [deadline yyyy-mm-dd]\"";
            } catch (DateTimeParseException dtpe) {
                return "Boss, you gotta enter the deadline date in the format: yyyy-mm-dd.";
            }

        case "event":
            try {
                String[] tokensNameAndDates = tokens[1].split(" /from ");
                String description = tokensNameAndDates[0];

                String[] tokensStartdateAndEnddate = tokensNameAndDates[1].split(" /to ");
                assert tokensStartdateAndEnddate.length == 2;
                String from = tokensStartdateAndEnddate[0];
                String to = tokensStartdateAndEnddate[1];
                c = new MakeEvent(description, from, to, tasklist, this);
                break;
            } catch (IndexOutOfBoundsException ioobe) {
                return "Boss, please give me an event in the format:" +
                        "\n\n\"event [name] /from [start] /to [end]\"";
            }

        case "delete":
            int idx = Integer.parseInt(tokens[1]);
            c = new Delete(idx, tasklist);

            //  Executing here to catch IndexOutOfBoundsException.
            return c.execute();

        case "find":
        try {
            String searchKey = tokens[1];
            c = new Find(searchKey, tasklist);
            break;
        } catch (IndexOutOfBoundsException ioobe) {
            return "Please enter a search phrase after \"find\".";
        }
        default:
            return "Boss, I'm sorry, but I don't understand :-(\n";
        }

        return c.execute();
    }

    public void handleDuplicates(Task t) {
        this.isCheckingDuplicates = true;
        this.duplicateTask = t;
    }
}
