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
        String errorMsg = saveToFile(s, l, ui, prevCommand);
        String markMsg;
        if (mark == 1) {
            l.get(index).markAsDone();
            markMsg = ui.showMark(1, l.get(index));
        } else if (mark == 0) {
            l.get(index).unmark();
            markMsg = ui.showMark(0, l.get(index));
        } else {
            throw new InvalidArgumentException(); //find a btr name
        }
        return ui.showFullReplyMsg(errorMsg, markMsg);
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
