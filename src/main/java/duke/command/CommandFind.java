package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a find command.
 */
public class CommandFind extends Command {
    private String word;

    /**
     * returns a find command.
     * @param command full unparsed command.
     * @param word keyword to find.
     */
    public CommandFind(String command, String word) {
        super(command);
        this.word = word;
    }

    /**
     * Prints the tasks that contain the keyword.
     * @param taskList contains the task list.
     * @param ui deals with interactions with the user.
     * @param storage deals with loading and saving tasks from file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.getLength(); i++) {
            if (taskList.getTask(i).getDesc().contains(word)) {
                System.out.printf("%d.%s\n", i + 1, taskList.getTask(i).toString());
            }
        }
    }
}
