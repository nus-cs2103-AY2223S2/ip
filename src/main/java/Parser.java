import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private Ui ui;
    private TaskList tasks;

    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public void parseInputs(String[] inputs, TaskList tasks) throws DukeException, IOException {
        String type = inputs[0];

        switch (type) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.getSize(); i++) {
                    System.out.println((i + 1) + "." + tasks.getTask(i));
                }
                break;

            case "mark":
                tasks.mark(true, inputs[1]);
                break;

            case "unmark":
                tasks.mark(false, inputs[1]);
                break;

            case "todo":
                checkTaskDesc(inputs);
                tasks.addToTasks(new Todo(inputs[1]));
                tasks.handleTaskOutput();
                break;

            case "deadline":
                checkTaskDesc(inputs);
                tasks.addToTasks(Deadline.createDeadline(inputs[1]));
                tasks.handleTaskOutput();
                break;

            case "event":
                checkTaskDesc(inputs);
                tasks.addToTasks(Event.createEvent(inputs[1]));
                tasks.handleTaskOutput();
                break;

            case "delete":
                int taskNo = Integer.parseInt(inputs[1]) - 1;
                tasks.deleteTask(taskNo);
                break;

            default:
                throw new InvalidTaskException();
        }
    }

    public static void checkTaskDesc(String[] splitStr) throws EmptyTaskException {
        if(splitStr.length == 1) {
            throw new EmptyTaskException(splitStr[0]);
        }
    }
}
