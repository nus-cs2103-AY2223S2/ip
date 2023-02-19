package wtd.command;

import java.util.ArrayList;
import java.util.Arrays;

import wtd.Storage;
import wtd.TaskList;
import wtd.Ui;
import wtd.exceptions.WtdException;
import wtd.exceptions.InvalidArgumentException;
import wtd.task.Task;

public class Remove extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("remove", "r"));
    private Integer index;

    public Remove(Integer i) {
        this.index = i;
    }

    @Override
    public String getCommand() {
        return "remove";
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WtdException {
        if (index > tasks.size() || index < 1) {
            throw new InvalidArgumentException(Integer.toString(index), "in the range of 1 to " + tasks.size());
        }
        Task t = tasks.get(index-1);
        Integer size = tasks.size();
        tasks.remove(index-1);
        assert size - 1 == tasks.size();
        return ui.showRemove(t, tasks.size());
    }

    public static boolean checkAlias(String alias) {
       return aliases.contains(alias);
    } 
}
