package sam.command;

import sam.Ui;
import sam.storage.Storage;
import sam.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.count() == 0) {
            ui.talk("Your list is empty!");
        } else {
            // "Here is your list:"
            String[] list = tasks.generateList();
            ui.talk(list);
        }
    }
}
