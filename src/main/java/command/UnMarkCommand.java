package command;

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
    public UnMarkCommand(String number) {
        this.number = Integer.parseInt(number) - 1;
    }

    /**
     * This method is used to unmark a certain task.
     */
    public void unmark() {
        try {
            Task curr = TaskList.get(number);
            curr.unmark();
            Ui.printUnmark(curr);
        } catch (Exception m){
            Ui.printWrongNumber();
            String s = Ui.readCommand();
            new UnMarkCommand(s).unmark();
        }
    }
}
