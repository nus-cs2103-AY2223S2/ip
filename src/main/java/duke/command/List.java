package duke.command;

import java.util.ArrayList;
import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

public class List extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("list", "l"));
    
    public List() {
        super();
    }

    @Override
    public String getCommand() {
        return "list";
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showList(tasks);
    };

    public static boolean checkAlias(String alias) {
       return aliases.contains(alias);
    } 
}
