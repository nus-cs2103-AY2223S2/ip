package duke.commands;

import duke.taskType.TaskList;
import duke.Ui;
import duke.Storage;

public class Bye extends Command {
    public Bye() {
    }

    public void operate(TaskList lst, Ui ui, Storage storage) {
        storage.save(lst);
        System.out.println("See you! Roarrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr!");
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
