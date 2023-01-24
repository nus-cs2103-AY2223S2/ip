public class AddDeadline implements Command{
    private String deadline;
    private String name;
    public AddDeadline(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        Deadline dl = new Deadline(this.name, this.deadline);
        tl.addTask(dl);
        storage.add(storage.getStorageTaskString(dl));
        ui.showAddDeadlineResult(dl.toString(), tl.getSize());
    }
}
