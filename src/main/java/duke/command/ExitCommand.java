package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String COMMAND_STRING) {
        super(Commands.EXIT, COMMAND_STRING);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String exitMsg = "Thank you for coming!\n" + "Hope to see you again soon!\n" + "~~Bye";
        ui.showMsg(exitMsg);

        storage.updateData(tasks);
        ui.close();
    }
}
