package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.taskType.TaskList;

public class notACommand extends Command {
    public notACommand() {
    }

    public void operate(TaskList lst, Ui ui, Storage storage) {
        System.out.println("Roarrrrrrrrrrrrrrrrr! What on earth are you talking about?");
    }
}
