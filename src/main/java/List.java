/**
 * The List command.
 * Lists down all the tasks.
 * Inherits from the superclass Command.
 */
public class List extends Command {
    private final String listOfTasks;

    public List() {
        this.listOfTasks = "all";
    }

    /**
     * The constructor of list.
     * @param listOfTasks The list of tasks.
     */
    public List(String listOfTasks) {
        this.listOfTasks = listOfTasks;
    }
}
