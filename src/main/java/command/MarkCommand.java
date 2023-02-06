package command;

import duke.MainWindow;
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
    private MainWindow mw = new MainWindow();
    public MarkCommand(String number) {
        this.number = Integer.parseInt(number) - 1;
    }

    /**
     * This method is used to mark a certain task.
     */
    public String mark() {
        try {
            Task curr = TaskList.get(number);
            curr.mark();
            return Ui.printMark(curr);
        } catch (Exception m){
            return Ui.printWrongNumber();
        }
    }
}
