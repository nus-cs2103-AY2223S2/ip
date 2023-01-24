import java.io.IOException;

public class Parser {
    public void parseInputs(String[] inputs, TaskList tasks) throws DukeException, IOException {
        String type = inputs[0];

        switch (type) {
            case "list":
                tasks.outputList();
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
