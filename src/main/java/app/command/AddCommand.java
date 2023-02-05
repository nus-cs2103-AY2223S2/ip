package app.command;

import java.util.Map;

import app.chatbot.Storage;
import app.chatbot.Ui;
import app.chatbot.Response;
import app.task.Task;
import app.task.TaskList;
import app.task.TaskTypes;


/**
 * Functions as an executor to convert user-input arguments
 * into a Task, and add into the TaskList.
 */
public class AddCommand extends Command {
    private Map<String, String> args;
    private TaskTypes.Type taskType;

    /**
     * Constructs an AddCommand.
     * @param type type of Task as specified by TaskTypes
     * @param args mapping of arguments needed for the Task and its arguments,
     *             created by the Parser
     */
    public AddCommand(TaskTypes.Type type, Map<String, String> args) {
        this.isExit = false;
        this.args = args;
        this.taskType = type;
        this.isSave = true;
    }

    /**
     * Adds a Task into the TaskList and informs the user of the Task added.
     * @param tl
     * @param ui
     * @param storage
     * @return String response
     * @throws Exception
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws Exception {
        Response response = new Response();
        Task newTask = tl.addTask(this.taskType, this.args);
        int numTasks = tl.getAllTasks().size();
        response.addLine("New task added:");
        response.addLine(newTask.toString());
        response.addLine("You now have " + numTasks + " task(s) in your list.");
        return response.toString();
    }
}
