package Duke.Command;

import Duke.Command.Command;
import Duke.Exceptions.InvalidArgumentException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class MarkCommand extends Command {

    private int mark;

    private int index;

    public MarkCommand(int mark, int index) {
        this.mark = mark;
        this.index = index;
    }

    public void execute(TaskList l, Ui ui, Storage s) throws InvalidArgumentException {
        if (mark == 1) {
            l.get(index).markAsDone();
            ui.showMark(1, l.get(index));
        } else if (mark == 0) {
            l.get(index).markAsDone();
            ui.showMark(1, l.get(index));
        } else {
            throw new InvalidArgumentException(); //find a btr name
        }
    }

    @Override
    public boolean equals(Object o) {
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
