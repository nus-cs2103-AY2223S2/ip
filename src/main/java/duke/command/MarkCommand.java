package duke.command;

import duke.Duke;
import duke.exceptions.InvalidArgumentException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {

    private int mark;
    private int index;

    public MarkCommand(int mark, int index) {
        this.mark = mark;
        this.index = index;
    }

    public String execute(TaskList l, Ui ui, Storage s, Command prevCommand, Duke duke) throws InvalidArgumentException {
        saveToFile(s, l, ui, prevCommand);
        if (mark == 1) {
            l.get(index).markAsDone();
            return ui.showMark(1, l.get(index));
        } else if (mark == 0) {
            l.get(index).unmark();
            return ui.showMark(0, l.get(index));
        } else {
            throw new InvalidArgumentException(); //find a btr name
        }
    }

    @Override
    public boolean equals(Object o) { //method might be faulty
        if (o == this) {
            return true;
        }
        if (!(o instanceof MarkCommand)) {
            return false;
        }
        MarkCommand c = (MarkCommand) o;
        return this.mark == c.mark && this.index == c.index;
    }
}
