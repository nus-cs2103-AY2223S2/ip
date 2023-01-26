package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddCommand extends Command{

    private String instruction;
    private String input;
    private boolean exit;

    public AddCommand(String instruction, String input) {
        this.instruction = instruction;
        this.input = input;
    }

    @Override
    public boolean isExit() {
        exit = false;
        return exit;
    }

    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        tasklist.addItem(instruction.toString() ,input);
    }
}
