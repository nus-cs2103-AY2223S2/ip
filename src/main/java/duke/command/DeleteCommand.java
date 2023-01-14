package duke.command;

import duke.constant.Message;
import duke.database.DukeRepo;
import duke.utils.Ui;

public class DeleteCommand extends Command {

    private int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(DukeRepo db, Ui ui) {
        try {
            ui.println(Message.DELETE_TASK);
            ui.println("\t" + db.removeTask(taskId));
            ui.println(String.format(Message.COUNT_TASK, db.count()));
        } catch (IndexOutOfBoundsException e) {
            ui.println(Message.EXCEPTION_INVALID_TODO_ID_ACCESS);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    } 
    
}
