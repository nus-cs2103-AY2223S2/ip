package duke.command;

import java.util.ArrayList;
import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

public class Find extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("find", "f"));
    private String keyword;

    public Find(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList matches = tasks.stream().filter(t -> t.getDescription().contains(keyword)).collect(TaskList::new, TaskList::add, TaskList::addAll);
        return ui.showFind(matches);
    };
    
    public static boolean checkAlias(String alias) {
       return aliases.contains(alias);
    } 
}
