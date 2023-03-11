package duke.command;
import duke.*;
public class MarkCommand extends Command {
    private int taskInt;
    private boolean isMarked;
    public MarkCommand(int taskInt, boolean isMarked) {
        this.taskInt = taskInt;
        this.isMarked = isMarked;
    }
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            return taskList.markTask(taskInt, isMarked);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e);
        } catch (NumberFormatException e) {
            throw new DukeException(e);
        } catch (Exception e) {
            throw new DukeException(e);
        }
    }
}
