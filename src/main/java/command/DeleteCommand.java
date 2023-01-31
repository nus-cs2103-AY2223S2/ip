package command;

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
    public DeleteCommand(String number) {
        this.number = Integer.parseInt(number) - 1;
    }

    /**
     * This method is used to delete a certain task from TaskList.
     */
    public void delete() {
        try {
            Task curr = TaskList.get(number);
            curr.minus();
            TaskList.remove(number);
            Ui.printDelete(curr);
        } catch (Exception m){
            Ui.printWrongNumber();
            String s = Ui.readCommand();
            new DeleteCommand(s).delete();
        }
    }
}
