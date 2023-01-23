package sam.command;

import java.util.List;

import sam.Ui;
import sam.storage.Storage;
import sam.task.TaskList;

public class FindCommand extends Command {
    public FindCommand(String args) {
        super(args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<String> list = tasks.findTasks(args);
        if (list.size() == 0) {
            ui.talk("None of your tasks match!");
        } else {
            list.add(0, String.format("I found %d matching tasks:", list.size()));
            ui.talk(list.toArray(new String[0]));
        }  
    }
}
