package duke.parser;
import duke.Storage;
import duke.TaskList;

/**
 * This class deals with user inputs
 *
 * @author He Shuimei
 * @version 0
 */
public class Parser {

    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor Parser object
     * @param taskList TaskList object
     * @param storage Storage object
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }
    /**
     * Returns an integer for different commands
     *
     * @param command string input from user
     * @param body content of the command
     */
    public String parseCommand(String command, String body) {
        switch(command) {
            case ("bye"):
                storage.writeFile(taskList.getTaskList());
                return "Goodbye";
            case ("list"):
                return taskList.listTask();
            case ("mark"):
                return taskList.markTaskDone(body);
            case ("unmark"):
                return taskList.markTaskNotDone(body);
            case ("delete"):
                return taskList.deleteTask(body);
            case ("todo"):
                return taskList.addTodo(body);
            case ("deadline"):
                return taskList.addDeadline(body);
            case ("event"):
                return taskList.addEvent(body);
            case("find"):
                return taskList.find(body);
            default:
                return taskList.unknownCommand(command);
        }
    }
}
