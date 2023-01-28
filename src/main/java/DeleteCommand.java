public class DeleteCommand extends Command {
    private int id;

    public DeleteCommand(String cmd) {
        this.id = Integer.parseInt(cmd.split(" ")[1]);
    }

    public boolean execute(Storage tl, Ui ui, Storage storage) {
        Task t6 = tl.removeTask(this.id - 1);
        System.out.println("Noted. I've removed this task:\n" + t6
                + "\n Now you have " + tl.getSize() + " tasks in the list.");
        return true;
    }
}
