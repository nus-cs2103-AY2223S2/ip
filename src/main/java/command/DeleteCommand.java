package command;

import duke.DukeException;
import duke.MainWindow;
import duke.Ui;

import task.Task;
import task.TaskList;

/**
 * This class will carry the implementation of the Delete Command
 * Takes in the command line command,parses as well as executing it
 *
 * @author Bryan Ong
 *
 */
public class DeleteCommand {
    private int number;
    private MainWindow mw = new MainWindow();
    public DeleteCommand(String number) {
        this.number = Integer.parseInt(number) - 1;
    }

    /**
     * This method is used to delete a certain task from TaskList.
     */
    public String delete() throws DukeException{
        try {
            Task curr = TaskList.get(number);
            UndoCommand.delete(curr, number);
            curr.minus();
            TaskList.remove(number);
            return Ui.printDelete(curr);
        } catch (Exception m){
            throw new DukeException(Ui.printWrongNumber());
        }
    }
}
