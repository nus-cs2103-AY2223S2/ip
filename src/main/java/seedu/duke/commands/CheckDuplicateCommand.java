package seedu.duke.commands;

import java.util.HashMap;

import seedu.duke.Storage;
import seedu.duke.Task;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class CheckDuplicateCommand extends Command {
    public static final String COMMAND = "check_duplicate";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (map.get(t.toString())!=null) {
                ui.indentedPrintln("There exist duplicate tasks! Tasks "
                        + (map.get(t.toString()) + 1) + " and " + (i + 1)  + " are duplicates.");
                return;
            }
            map.put(t.toString(), i);
        }
        ui.indentedPrintln("There's no duplicate task :)");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
