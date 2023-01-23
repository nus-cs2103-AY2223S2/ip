package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    private String input;

    public ByeCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTaskList(tasks);
        System.out.println("    " + "Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
