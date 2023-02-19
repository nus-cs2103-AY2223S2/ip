package wtd.command;

import java.util.ArrayList;
import java.util.Arrays;

import wtd.Storage;
import wtd.TaskList;
import wtd.Ui;
import wtd.exceptions.WtdException;

public class Exit extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("bye", "b"));

    public Exit() {
        super();
    }

    @Override
    public String getCommand() {
        return "bye";
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WtdException {
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
