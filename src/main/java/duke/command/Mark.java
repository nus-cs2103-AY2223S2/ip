package duke.command;

import java.util.ArrayList;
import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidArgumentException;
import duke.task.Task;

public class Mark extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("mark", "m"));
    private Integer index;

    public Mark(Integer i) {
        this.index = i;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
