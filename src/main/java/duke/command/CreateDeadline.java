package duke.command;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

public class CreateDeadline extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("deadline", "dl"));
    private String desc;
    private LocalDate by;

    public CreateDeadline(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Integer size = tasks.size();
        tasks.add(new Deadline(desc, by, false));
        assert size + 1 == tasks.size();
        return ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }

   public static boolean checkAlias(String alias) {
      return aliases.contains(alias);
   } 
}
