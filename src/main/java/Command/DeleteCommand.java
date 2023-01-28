package Command;

import Duke.Ui;
import Task.Task;
import Task.TaskList;

public class DeleteCommand {
    private int number;
    public DeleteCommand(String number) {
        this.number = Integer.parseInt(number) - 1;
    }

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
