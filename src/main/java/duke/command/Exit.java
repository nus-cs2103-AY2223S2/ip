package duke.command;

import java.util.ArrayList;
import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

public class Exit extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("bye", "b"));

    public Exit() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.save(tasks);
        return ui.showBye();
    };

    @Override
    public boolean isExit() {
        return true;
    }

    public static boolean checkAlias(String alias) {
       return aliases.contains(alias);
    } 
}
