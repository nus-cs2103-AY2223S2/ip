public class OccursCommand extends Command {
    private String input;

    OccursCommand(String input) {
        super("list");
        this.input = input;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Handle occurs
        String dateTime = this.input.substring(7);

        // Print tasks
        try {
            tl.printTasksOnDate(dateTime);
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
