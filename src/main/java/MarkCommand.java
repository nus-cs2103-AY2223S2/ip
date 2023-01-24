public class MarkCommand extends Command {
    private String input;

    MarkCommand(String input) {
        super("mark \\d+");
        this.input = input;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Marking a task as done
        Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

        // Verify if task number is invalid:
        if (idx < 0 || idx >= tl.numberOfTasks()) {
            throw new InvalidCommandInputException("Task number is invalid!", "mark");
        }

        tl.markTask(idx);
        storage.save(tl);

        System.out.println("Nice! I've marked this task as done:\n" + tl.getTask(idx));
    }
}
