package command;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;
import util.Util;

/**
 * Manages help command
 */
public class Help implements Command {
    private static final String COMMAND_LST =
            Util.join("\n",
                    "todo",
                    "deadline",
                    "event",
                    "delete",
                    "find",
                    "findbydate",
                    "list",
                    "mark",
                    "unmark",
                    "sort",
                    "bye");
    private static final String ERROR = "Command does not exist.";
    private final String command;

    private Help(String command) {
        this.command = command;
    }

    /**
     * @return Parser that parses help command.
     */
    public static Parser<Command> parser() {
        return Parser.nextStrIgnoreCase("help")
                .ignoreThen(Parser.nextStr().or(Parser.strParser("")))
                .map(String::toLowerCase)
                .map(Help::new);
    }

    /**
     * {@inheritDoc}
     */
    public void execute(TaskList taskList, Ui ui, Storage<TaskList> storage) {
        switch (command) {
        case "todo":
            ui.showReply(Add.HELP_MSG_TODO);
            break;
        case "deadline":
            ui.showReply(Add.HELP_MSG_DEADLINE);
            break;
        case "event":
            ui.showReply(Add.HELP_MSG_EVENT);
            break;
        case "delete":
            ui.showReply(Delete.HELP_MSG);
            break;
        case "bye":
            ui.showReply(Exit.HELP_MSG);
            break;
        case "find":
            ui.showReply(Find.HELP_MSG);
            break;
        case "findbydate":
            ui.showReply(FindByDate.HELP_MSG);
            break;
        case "list":
            ui.showReply(List.HELP_MSG);
            break;
        case "mark":
            ui.showReply(Mark.HELP_MSG);
            break;
        case "unmark":
            ui.showReply(Unmark.HELP_MSG);
            break;
        case "sort":
            ui.showReply(Sort.HELP_MSG);
            break;
        case "all":
        case "":
            ui.showReply(COMMAND_LST);
            break;
        default:
            ui.showError(ERROR);
            break;
        }
    }
}
