package commands;

import exceptions.EmptyTaskException;
import parser.Parser;
import storage.Storage;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

import java.io.IOException;

public class UpdateCommand extends Command {
    public UpdateCommand(String string) {
        super(string);
    }

    /**
     * Execute update command
     * @param tasks the current list of tasks
     * @param ui the user interface
     * @param storage the storage where the changes done by command action stored
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            String[] request = super.getCommand().split(" ", 2);
            int idx = Integer.parseInt(request[0]) - 1;
            String command = request[1];
            Command c = Parser.parse(command);
            c.replace(tasks, ui, storage, idx);
        } catch (Exception e) {
            ui.idxErrorMsg();
        }
    }

    public String generate(TaskList tasks, Ui ui, Storage storage) {
        return ui.printReplace();
    }

}
