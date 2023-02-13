package command;

import duke.Ui;
import task.TaskList;

/**
 * Command to find all tasks that matches a phrase.
 */
public class CommandFind extends Command {

    private final TaskList taskList;
    private final String phrase;

    /**
     * Constructor for CommandFind.
     *
     * @param taskList List of all tasks.
     * @param phrase Phrase use to find task.
     */
    public CommandFind(TaskList taskList, String phrase) {
        this.taskList = taskList;
        this.phrase = phrase;
    }

    @Override
    public String execute() {
        String foundTasks = this.findAllTaskContaining(this.phrase);
        return this.format(foundTasks);
    }

    private String findAllTaskContaining(String phrase) {
        return this.taskList.findTaskWith(phrase);
    }

    private String format(String foundTasks) {
        return Ui.getAllTaskFoundMessageWithAttitude(foundTasks);
    }
}
