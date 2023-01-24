public class DeleteCommand extends Command {
    private String input;

    DeleteCommand(String input) {
        super("delete \\d+");
        this.input = input;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Deleting a task
        Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

        // Verify if task number is invalid:
        if (idx < 0 || idx >= tl.numberOfTasks()) {
            throw new InvalidCommandInputException("Task number is invalid!", "delete");
        }

        Task t = tl.deleteTask(idx);
        storage.save(tl);

        System.out.println("Noted. I've removed this task:\n" + t +
                "\nNow you have " + tl.numberOfTasks() + (tl.numberOfTasks() > 1 ? " tasks" : " task") + " in the list.");
    }
}
