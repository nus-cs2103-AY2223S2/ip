import exceptions.DukeException;

/***
 * this class handles deleting a task as a command
 */
public class DeleteCommand extends Command{

    private int index;

    /**
     * Constructor for DeleteCommand
     * @param index
     */
    public DeleteCommand(int index){
        super();
        this.index = index;
    }

    /***
     * removes the task from taskList and updates the storage file
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int taskNumMinusOne = index - 1;
        Task taskToDelete = taskList.get(taskNumMinusOne);
        taskList.remove(taskNumMinusOne);
        storage.writeFile(taskList);
        return ui.printDelete(index, taskToDelete,taskList);
    }
}


