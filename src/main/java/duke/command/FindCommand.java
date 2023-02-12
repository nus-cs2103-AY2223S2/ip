package duke.command;

import java.util.ArrayList;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class FindCommand extends Command {

    protected String input;

    /**
     * Initialises the object
     * @param input The task user wants to find
     */
    public FindCommand(String input) {

        assert !input.equals("");

        this.input = input;
    }

    /**
     * Executes the instruction
     * @param list The task list object
     * @param ui The ui object
     * @param storage The storage object
     */
    public String execute(TaskList list, Ui ui, Storage storage) {
        ArrayList<Integer> found = new ArrayList<>();
        for (Integer i = 0; i < list.getLength(); i++) {
            String cur = list.getTask(i).toString();
            //Add the index of each task found to the found list
            if (cur.contains(input)) {
                found.add(i);
            }
        }
        return ui.find(found, list);
    }

    /**
     * Tells if this is the exit command
     * @return
     */
    public boolean isExit() {
        return false;
    }
}
