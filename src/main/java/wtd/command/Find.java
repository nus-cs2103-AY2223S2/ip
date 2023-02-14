package wtd.command;

import java.util.ArrayList;
import java.util.Arrays;

import wtd.Storage;
import wtd.TaskList;
import wtd.Ui;
import wtd.exceptions.WtdException;

public class Find extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("find", "f"));
    private String keyword;

    public Find(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String getCommand() {
        return "find";
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WtdException {
        TaskList matches = tasks.stream().filter(t -> t.getDescription().contains(keyword)).collect(TaskList::new, TaskList::add, TaskList::addAll);
        return ui.showFind(matches);
    };
    
    public static boolean checkAlias(String alias) {
       return aliases.contains(alias);
    } 
}
