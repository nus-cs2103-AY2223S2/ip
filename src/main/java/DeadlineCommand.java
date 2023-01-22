public class DeadlineCommand extends Command {

    private final String NAME = "deadline";
    private String title;
    private String deadline;

    public DeadlineCommand(String title, String deadline) {
        this.title = title;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Task task = new Deadline(this.title, this.deadline);
        taskList.addTask(task);
    }
}
