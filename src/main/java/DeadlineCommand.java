import exceptions.DukeException;


/**
 * this class handles when a deadline command is called and executed
 */
public class DeadlineCommand extends Command{

    private String input;

    /**
     * constructor for Deadline Command
     * @param input
     */
    public DeadlineCommand(String input){
        super();
        this.input = input;
    }

    /***
     * splits the input and adds a deadline task into TaskList
     * if duplicate detected, no changes made to taskList or storage
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        input = input.substring(9);
        String[] split = input.split("/by");
        String description = split[0].trim();
        String time = split[1].trim();
        Task task = new Deadline(description, time);
            taskList.add(task);
            storage.writeFile(taskList);
            return ui.printAddTask(taskList.size(),task);
    }
}