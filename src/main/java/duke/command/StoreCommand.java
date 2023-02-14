package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.CommandException;
import duke.exception.DukeException;
import duke.exception.FileException;
import duke.task.TaskList;

public class StoreCommand extends Command{
    private String filepath;
    private Storage storage;
    private TaskList tasks;
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        return ui.showStored(this.storage, this.tasks);
    }

    public StoreCommand(String input) throws FileException {
        this.filepath = input;
        this.storage = new Storage(this.filepath);
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (CommandException commandException) {
            this.tasks = new TaskList();
            throw new FileException();
        }
    }


    public TaskList getTasks() {
        return tasks;
    }

    public Storage getStorage() {
        return storage;
    }
}
