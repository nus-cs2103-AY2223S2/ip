package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.textui.TextUi;

/**
 * A command that stores the command to find all tasks that match the specific term as a substring. The action of
 * finding the tasks that match can be carried out when called.
 */
public class FindCommand extends Command {
    /**
     * The finding term.
     */
    private final String keyword;

    /**
     * Constructor for a command to find all tasks that match the specific keyword as a substring.
     *
     * @param keyword The finding keyword
     */
    public FindCommand(String keyword) {
        super(AvailableCommands.FIND);
        this.keyword = keyword;
    }

    /**
     * Finds all tasks that matches the finding term. Display out a numbered list of all of these tasks.
     *
     * @param taskList List of tasks that are stored
     * @param ui       UI to deal with the visual output
     * @param storage  Storage to deal with input and output of data
     * @return The string of what is printed out after execution
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) throws DukeException {
        String msgHeader = "These are the tasks with matching descriptions in them:";
        StringBuilder output = new StringBuilder(ui.showMsg(msgHeader));

        int counter = 1;
        for (Task task : taskList.getTasks()) {
            if (task.isInDescription(keyword)) {
                String msg = String.format("%d. %s", counter++, task);
                output.append("\n");
                output.append(ui.showMsg(msg));
            }
        }
        return output.toString();
    }
}
