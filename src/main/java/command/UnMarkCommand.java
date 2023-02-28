package command;

import duke.DukeException;
import duke.MainWindow;
import duke.Ui;

import task.Task;
import task.TaskList;

/**
 * This class will carry the implementation of the Unmark Command
 * Takes in the command line command,parses as well as executing it.
 *
 * @author Bryan Ong
 *
 */
public class UnMarkCommand {
    private int number;
    private MainWindow mw = new MainWindow();
    public UnMarkCommand(String number) {
        this.number = Integer.parseInt(number) - 1;
    }

    /**
     * This method is used to unmark a certain task.
     */
    public String unmark() throws DukeException {
        try {
            Task curr = TaskList.get(number);
            UndoCommand.unmark(number);
            curr.unmark();
            return Ui.printUnmark(curr);
        } catch (Exception m){
            throw new DukeException(Ui.printWrongNumber());
        }
    }
}
