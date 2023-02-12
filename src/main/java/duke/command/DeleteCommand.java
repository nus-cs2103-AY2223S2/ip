package duke.command;
import duke.*;
public class DeleteCommand extends Command { 
    private int taskInt;
    public DeleteCommand(int taskInt) {
        this.taskInt = taskInt;
    }
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            taskList.deleteTask(taskInt);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e);
        } catch (NumberFormatException e) {
            throw new DukeException(e);
        } catch (Exception e) {
            throw new DukeException(e);
        }
    }
}
