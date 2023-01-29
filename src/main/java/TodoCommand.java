public class TodoCommand extends Command {
    String description;
    public TodoCommand(String rest) throws LuluException {
        if (rest.isEmpty()) {
            throw new LuluException("(=✖ ᆺ ✖=) The description of a todo cannot be empty.");
        }
        this.description = rest;
    }
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = new Todo(description);
        tasks.add(t);
        ui.showAddText(tasks.getRecentTaskDescription(), tasks.getSize());
    }
}