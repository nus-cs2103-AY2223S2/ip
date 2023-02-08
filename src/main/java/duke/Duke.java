package duke;
import java.io.IOException;
import java.util.Scanner;

/**
 * Duke object which runs the chatbot.
 */
public class Duke {
    private TaskList taskList = new TaskList();
    private Parser parser = new Parser();
    private Storage storage = new Storage(taskList);
    private Ui ui = new Ui();
    private Task task;

    /**
     * Runs Duke chatbot
     * @throws IOException Thrown when an invalid commmand is input.
     */
    public void runDuke() throws IOException {
        Scanner sc = new Scanner(System.in);
        String[] parsedCommand;
        String formattedReply;
        int taskIndex;
        taskList = storage.loadTasks();

        ui.greet();
        while (true) {
            String userInput = sc.nextLine().toLowerCase();
            try {
                parsedCommand = parser.parseCommand(userInput);
                switch (parsedCommand[0]) {
                case "todo":
                    Task newTodo = new Todo(parsedCommand[1]);
                    taskList.addTask(newTodo);
                    formattedReply = ui.formatAddTaskReply(taskList, newTodo);
                    ui.reply(formattedReply);
                    break;
                case "deadline":
                    Task newDeadline = new Deadline(parsedCommand[1], parsedCommand[2]);
                    taskList.addTask(newDeadline);
                    formattedReply = ui.formatAddTaskReply(taskList, newDeadline);
                    ui.reply(formattedReply);
                    break;
                case "event":
                    Task newEvent = new Event(parsedCommand[1], parsedCommand[2], parsedCommand[3]);
                    taskList.addTask(newEvent);
                    formattedReply = ui.formatAddTaskReply(taskList, newEvent);
                    ui.reply(formattedReply);
                    break;
                case "list":
                    formattedReply = String.format(
                            "Here are the tasks in your list:\n%s", taskList.getTaskList());
                    ui.reply(formattedReply);
                    break;
                case "delete":
                    taskIndex = Integer.parseInt(parsedCommand[1]);
                    taskList.delTask(taskIndex);
                    break;
                case "mark":
                    taskIndex = Integer.parseInt(parsedCommand[1]);
                    taskList.markTask(taskIndex - 1);
                    formattedReply = String.format(
                            "Nice! I've marked this task as done:\n%s", taskList.getTask(taskIndex - 1));
                    ui.reply(formattedReply);
                    break;
                case "unmark":
                    taskIndex = Integer.parseInt(parsedCommand[1]);
                    taskList.unmarkTask(taskIndex - 1);
                    formattedReply = String.format(
                            "OK, I've marked this task as not done yet:\n%s", taskList.getTask(taskIndex - 1));
                    ui.reply(formattedReply);
                    break;
                case "find":
                    formattedReply = String.format(
                            "Here are the matching tasks in your list:\n%s",
                            taskList.findTasks(parsedCommand[1]));
                    ui.reply(formattedReply);
                    break;
                case "bye":
                    ui.printExitMessage();
                    break;
                default:
                    throw new InvalidCommandException("Sorry, I don't understand that command, try again.");
                }
            } catch (InvalidCommandException e) {
                ui.reply(e.getMessage());
                continue;
            }
            if (userInput.equals("bye")) {
                storage.saveTasks();
                break;
            }
        }
    }
}
