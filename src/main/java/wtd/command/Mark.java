package wtd.command;

import java.util.ArrayList;
import java.util.Arrays;

import wtd.Storage;
import wtd.TaskList;
import wtd.Ui;
import wtd.exceptions.WtdException;
import wtd.exceptions.InvalidArgumentException;
import wtd.task.Task;

public class Mark extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("mark", "m"));
    private Integer index;

    public Mark(Integer i) {
        this.index = i;
    }

    @Override
    public String getCommand() {
        return "mark";
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WtdException {
        if (index > tasks.size() || index < 1) {
            throw new InvalidArgumentException(Integer.toString(index), "in the range of 1 to " + tasks.size());
        }
        Task t = tasks.get(index-1);
        t.setDone();
        return ui.showMark(t);
    }

    public static boolean checkAlias(String alias) {
       return aliases.contains(alias);
    } 
}
