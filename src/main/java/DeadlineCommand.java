public class DeadlineCommand extends Command {
    private String title;
    private String deadline;

    DeadlineCommand(String title, String deadline) {
        this.title = title;
        this.deadline = deadline;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Deadline(this.title,
                this.deadline);
        tasks.add(newTask);
        ui.showAdd(newTask);
        storage.save(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
