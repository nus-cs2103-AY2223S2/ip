public class EventCommand extends Command {
    String description;
    String from;
    String to;
    public EventCommand(String rest) throws LuluException {
        if (rest.isEmpty()) {
            throw new LuluException("(=✖ ᆺ ✖=) The description of a event cannot be empty.");
        }
        String[] eventDetails = rest.split("/from");
        if (eventDetails.length == 1) {
            throw new LuluException("(=✖ ᆺ ✖=) Please include a timing using /from and /to");
        }
        String[] toFrom = eventDetails[1].split("/to");
        if (toFrom.length == 1) {
            throw new LuluException("(=✖ ᆺ ✖=) You included /from but missed out /to");
        }
        this.description = eventDetails[0];
        this.from = toFrom[0];
        this.to = toFrom[1];
    }
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Task t = new Event(description, from, to);
        tasks.add(t);
        ui.showAddText(tasks.getRecentTaskDescription(), tasks.getSize());
    }
}
