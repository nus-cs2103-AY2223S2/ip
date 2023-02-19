package wtd.command;

import java.util.ArrayList;
import java.util.Arrays;

import wtd.Storage;
import wtd.TaskList;
import wtd.Ui;
import wtd.exceptions.WtdException;

public class List extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("list", "l"));
    
    public List() {
        super();
    }

    @Override
    public String getCommand() {
        return "list";
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws WtdException {
        return ui.showList(tasks);
    };

    public static boolean checkAlias(String alias) {
       return aliases.contains(alias);
    } 
}
