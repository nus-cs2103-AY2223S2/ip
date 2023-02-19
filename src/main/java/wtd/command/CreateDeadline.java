package wtd.command;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.ArrayList;

import wtd.Storage;
import wtd.TaskList;
import wtd.Ui;
import wtd.exceptions.WtdException;
import wtd.task.Deadline;

public class CreateDeadline extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("deadline", "d"));
    private String desc;
    private LocalDate by;

    public CreateDeadline(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public String getCommand() {
        return "deadline";
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WtdException {
        Integer size = tasks.size();
        tasks.add(new Deadline(desc, by, false));
        assert size + 1 == tasks.size();
        return ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }

   public static boolean checkAlias(String alias) {
      return aliases.contains(alias);
   } 
}
