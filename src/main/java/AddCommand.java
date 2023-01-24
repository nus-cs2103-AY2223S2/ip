public class AddCommand extends Command {
    private String input;

    AddCommand(String input) {
        super("");
        this.input = input;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Add task
        Task t = tl.addTask(input);
        storage.save(tl);
        System.out.println("Got it. I've added this task:\n" + t +
                "\nNow you have " + tl.numberOfTasks() + (tl.numberOfTasks() > 1 ? " tasks" : " task") + " in the list.");
    }
}
