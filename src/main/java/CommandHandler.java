import dukeexceptions.IllegalCommandException;
import dukeexceptions.IllegalInputException;
import items.Deadline;
import items.Event;
import items.Task;
import items.ToDo;

import java.time.LocalDate;

public class CommandHandler {

    private TaskList taskList;

    public CommandHandler(TaskList taskList) {
        this.taskList = taskList;
    }

    public void execute(String command) throws IllegalCommandException,
            IllegalInputException, NumberFormatException {
        String[] parsedCommand = command.split(" ", 2);
        switch (parsedCommand[0]) {
            case "bye":
                if (parsedCommand.length > 1) {
                    throw new IllegalCommandException("Not sure if you want to say goodbye?");
                }
                System.out.println("Goodbye!");
                System.exit(0);
            case "list":
                if (parsedCommand.length > 1) {
                    throw new IllegalCommandException("Did you mean you want to list the items in your list?");
                }
                this.taskList.enumerate();
                break;
            case "mark":
                if (parsedCommand.length > 2) {
                    throw new IllegalInputException("Too many arguments!");
                }
                int taskCode = Integer.parseInt(parsedCommand[1]);
                this.taskList.markTask(taskCode);
                break;
            case "unmark":
                int taskCodeUnmark = Integer.parseInt(parsedCommand[1]);
                this.taskList.unmarkTask(taskCodeUnmark);
                break;
            case "todo":
                try {
                    Task newToDo = new ToDo(parsedCommand[1]);
                    this.taskList.addTask(newToDo);
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalInputException("You did not key in a task name!");
                }
            case "deadline":
                String[] paramsDeadline = parsedCommand[1].split("/");
                Task newDeadline = new Deadline(paramsDeadline[0], LocalDate.parse(paramsDeadline[1]));
                this.taskList.addTask(newDeadline);
                break;
            case "event":
                String[] paramsEvent = parsedCommand[1].split("/");
                Task newEvent = new Event(paramsEvent[0], paramsEvent[1], paramsEvent[2]);
                this.taskList.addTask(newEvent);
                break;
            case "delete":
                int taskCodeDelete = Integer.parseInt(parsedCommand[1]);
                this.taskList.delete(taskCodeDelete);
                break;
            default:
                throw new IllegalCommandException("You did not key in a valid command");
        }
    }
}
