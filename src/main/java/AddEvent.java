public class AddEvent implements Command {
    private String from;
    private String to;
    private String name;
    public AddEvent(String name, String from, String to) {
        this.from = from;
        this.to = to;
        this.name = name;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        Event ev = new Event(this.name, this.from, this.to);
        tl.addTask(ev);
        storage.add(storage.getStorageTaskString(ev));
        ui.showAddEventResult(ev.toString(), tl.getSize());
    }
}
