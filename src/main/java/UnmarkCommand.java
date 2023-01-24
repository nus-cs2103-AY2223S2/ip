public class UnmarkCommand extends Command {
    private String input;

    UnmarkCommand(String input) {
        super("unmark \\d+");
        this.input = input;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Unmarking a task
        Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

        // Verify if task number is invalid:
        if (idx < 0 || idx >= tl.numberOfTasks()) {
            throw new InvalidCommandInputException("Task number is invalid!", "unmark");
        }

        tl.unmarkTask(idx);
        storage.save(tl);

        System.out.println("OK, I've marked this task as not done yet:\n" + tl.getTask(idx));
    }
}
