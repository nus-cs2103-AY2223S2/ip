package roody.commands;

import java.util.ArrayList;

import roody.Storage;
import roody.Ui;
import roody.exceptions.RoodyException;
import roody.tasks.Task;

/**
 * Represents a command to help the user.
 */
public class HelpCommand extends Command {
    static final String START_HELP = "Woof! What do you need help in?\n";
    static final String CMD_LIST = "    - For commands, type \"help c\"\n"
            + "    - For task creation, type \"help tc\"\n"
            + "    - For task management, type \"help tm\"\n";
    static final String CMD_HELP = "Here are a list of commands you can use!\n"
            + "     Task Creation: [todo, deadline, event]\n"
            + "     Task Management: [list, delete, mark, unmark, find]\n"
            + "     Task saving: [bye]\n";
    static final String TASK_CREATION_HELP = "Here are a few ways to make tasks!\n"
            + "     - todo {desc}\n"
            + "     - deadline {desc} /by {date}\n"
            + "     - event {desc} /from {date} /to {date}\n";
    static final String TASK_MANAGEMENT_HELP = "Here are a few ways you can manage your tasks!\n"
            + "     - delete {task number}\n"
            + "     - mark/unmark {task number}\n"
            + "     - find {keyword}\n";

    private String query;

    /**
     * Creates a help command.
     * @param query Help that user requires.
     */
    public HelpCommand(String query) {
        this.query = query;
    }
    @Override
    public String execute(ArrayList<Task> taskList, Ui ui, Storage storage) throws RoodyException {
        switch(query) {
        case "start":
            return START_HELP + CMD_LIST;
        case "c":
            return CMD_HELP;
        case "tc":
            return TASK_CREATION_HELP;
        case "tm":
            return TASK_MANAGEMENT_HELP;
        default:
            return "Oh no :( I don't quite understand that.\n" + CMD_LIST;
        }
    }
}
