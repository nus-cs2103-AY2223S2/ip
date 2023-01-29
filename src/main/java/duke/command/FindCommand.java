package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private final String TERM;

    public FindCommand(String commandString, String term) {
        super(Commands.FIND, commandString);
        TERM = term;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String msgHeader = "These are the tasks with matching descriptions in them:";
        ui.showMsg(msgHeader);

        int counter = 1;
        for (Task task : taskList.getTasks()) {
            if (task.isInDescription(TERM)) {
                String output = String.format("%d. %s", counter++, task);
                ui.showMsg(output);
            }
        }
    }
}
