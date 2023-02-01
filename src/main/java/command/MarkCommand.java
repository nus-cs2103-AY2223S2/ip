package command;

import duke.Ui;

import task.Task;
import task.TaskList;

/**
 * This class will carry the implementation of the Mark Command
 * Takes in the command line command,parses as well as executing it.
 *
 * @author Bryan Ong
 *
 */
public class MarkCommand {

    private int number;
    public MarkCommand(String number) {
        this.number = Integer.parseInt(number) - 1;
    }

    /**
     * This method is used to mark a certain task.
     */
    public void mark() {
        try {
            Task curr = TaskList.get(number);
            curr.mark();
            Ui.printMark(curr);
        } catch (Exception m){
            Ui.printWrongNumber();
            String s = Ui.readCommand();
            new MarkCommand(s).mark();
        }
    }
}
