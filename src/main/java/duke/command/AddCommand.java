package duke.command;

import duke.Ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class AddCommand extends Command{
    protected String fullCommand;
    protected String[] s;
    public AddCommand(String fullCommand, String[] s) {
        this.fullCommand = fullCommand;
        this.s = s;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (s[0].equals("todo")){
            tasks.todo(s);
        } else if (s[0].equals("deadline")) {
            tasks.deadline(s);
        } else if (s[0].equals("event")) {
            tasks.event(s);
        }
    }

    /**
     * Tests if at end of command stack
     *
     * @return false if not at end, true if no more commands left
     */

    @Override
    public boolean isExit() {
        return false;
    }
}
