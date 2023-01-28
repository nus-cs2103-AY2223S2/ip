import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {
    private String taskType;
    private String taskDesc;
    private String deadline;
    private String from;
    private String by;

    public AddCommand(String taskType, String taskDesc) {
        this.taskType = taskType;
        this.taskDesc = taskDesc;
    }

    public AddCommand(String taskType, String taskDesc, String deadline) {
        this.taskType = taskType;
        this.taskDesc = taskDesc;
        this.deadline = deadline;
    }

    public AddCommand(String taskType, String taskDesc, String from, String by) {
        this.taskType = taskType;
        this.taskDesc = taskDesc;
        this.from = from;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (taskType) {
            case "todo":
                    Todo todo = new Todo(taskDesc);
                    tasks.addTask(todo);
                    ui.showAddTaskMsg(todo, String.valueOf(tasks.getLength()));
                    break;
            case "deadline":
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                    LocalDateTime dueDate = LocalDateTime.parse(deadline.substring(3), formatter);
                    Deadline deadline = new Deadline(taskDesc, dueDate);
                    tasks.addTask(deadline);
                    ui.showAddTaskMsg(deadline, String.valueOf(tasks.getLength()));
                } catch (DateTimeException e) {
                    System.out.println("ERROR!! Please key in valid date format: dd-MM-yyyy HHmm");
                } finally {
                    break;
                }
            case "event":
                    Event event = new Event(taskDesc, from.substring(5), by.substring(3));
                    tasks.addTask(event);
                    ui.showAddTaskMsg(event, String.valueOf(tasks.getLength()));
                    break;
            default:
                break;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
