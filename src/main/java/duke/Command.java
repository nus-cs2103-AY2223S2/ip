package duke;

/**
 *
 */
public class Command {
    private Ui ui;
    private String[] parsedCommand;
    private TaskList taskList;

    public Command(Ui ui, TaskList taskList, String[] parsedCommand) {
        this.ui = ui;
        this.parsedCommand = parsedCommand;
        this.taskList = taskList;
    }

    private String getAddTaskReply() {
        String formattedReply;
        Task latestTask;

        latestTask = taskList.getLatestTask();
        formattedReply = ui.formatAddTaskReply(latestTask);
        return formattedReply;
    }

    private String getDelTaskReply() {
        String formattedReply;
        int taskIndex;
        Task deletedTask;

        taskIndex = Integer.parseInt(parsedCommand[1]);
        deletedTask = taskList.getTask(taskIndex - 1);
        formattedReply = ui.formatDelTaskReply(deletedTask);
        return formattedReply;
    }

    private String getListTasksReply() {
        return ui.formatlistTasksReply();
    }

    private String getMarkTaskReply() {
        String formattedReply;
        int taskIndex;
        Task markedTask;

        taskIndex = Integer.parseInt(parsedCommand[1]);
        markedTask = taskList.getTask(taskIndex - 1);
        formattedReply = ui.formatMarkTaskReply(markedTask);
        return formattedReply;
    }

    private String getUnmarkTaskReply() {
        String formattedReply;
        int taskIndex;
        Task unmarkedTask;

        taskIndex = Integer.parseInt(parsedCommand[1]);
        unmarkedTask = taskList.getTask(taskIndex - 1);
        formattedReply = ui.formatUnmarkTaskReply(unmarkedTask);
        return formattedReply;
    }

    private String getFindTaskReply() {
        String keyword = parsedCommand[1];
        return String.format(
                "Here are the matching tasks in your list:\n%s",
                taskList.findTasks(keyword));
    }

    private void createAndAddTodo() {
        Task newTodo = new Todo(parsedCommand[1]);
        taskList.addTask(newTodo);
    }

    private void createAndAddDeadline() {
        Task newDeadline = new Deadline(parsedCommand[1], parsedCommand[2]);
        taskList.addTask(newDeadline);
    }

    private void createAndAddEvent() {
        Task newEvent = new Event(parsedCommand[1], parsedCommand[2], parsedCommand[3]);
        taskList.addTask(newEvent);
    }

    private void deleteFromTaskList() {
        int taskIndex;

        taskIndex = Integer.parseInt(parsedCommand[1]);
        taskList.delTask(taskIndex);
    }

    private void executeMarkTask() {
        int taskIndex;

        taskIndex = Integer.parseInt(parsedCommand[1]);
        taskList.markTask(taskIndex - 1);
    }

    private void executeUnmarkTask() {
        int taskIndex;

        taskIndex = Integer.parseInt(parsedCommand[1]);
        taskList.unmarkTask(taskIndex - 1);
    }

    public String execute() throws InvalidCommandException {
        String formattedReply;

        switch (parsedCommand[0]) {
        case "todo":
            createAndAddTodo();
            formattedReply = getAddTaskReply();
            break;
        case "deadline":
            createAndAddDeadline();
            formattedReply = getAddTaskReply();
            break;
        case "event":
            createAndAddEvent();
            formattedReply = getAddTaskReply();
            break;
        case "list":
            formattedReply = getListTasksReply();
            break;
        case "delete":
            formattedReply = getDelTaskReply();
            deleteFromTaskList();
            break;
        case "mark":
            executeMarkTask();
            formattedReply = getMarkTaskReply();
            break;
        case "unmark":
            executeUnmarkTask();
            formattedReply = getUnmarkTaskReply();
            break;
        case "find":
            formattedReply = getFindTaskReply();
            break;
        default:
            throw new InvalidCommandException("Sorry, I don't understand that command, try again.");
        }
        return formattedReply;
    }
}
