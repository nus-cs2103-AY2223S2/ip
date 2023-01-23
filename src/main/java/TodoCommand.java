import exceptions.DukeException;

/**
 * This class handles when a Todo task is added
 */
public class TodoCommand extends Command{

    private String input;

    /***
     * Constructor for TodoCommand
     * @param input
     */
    public TodoCommand(String input){
        super();
        this.input = input;
    }

    /***
     * adds todo task into Tasklist and updates storage
     * if duplicate detected, no changes made to taskList or storage
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task= new Todo(input);
            taskList.add(task);
            storage.writeFile(taskList);
            return ui.printAddTask(taskList.size(), task);
    }
}