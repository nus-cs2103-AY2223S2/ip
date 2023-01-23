/**
 * This class handles listing the entire TaskList
 */
public class ListCommand extends Command {

    /**
     * Constructor for listCommand
     */
    public ListCommand(){
        super();
    }

    /**
     * Prints the entire taskList
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printList(taskList);
    }
}
