public class AddEventCommand extends Command {
    private String data;

    public AddEventCommand(String commandString, String data) {
        super(Commands.ADD_EVENT, commandString);
        this.data = data;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] splitData1 = data.split(" /from ", 2);
        if (splitData1.length < 2) {
            throw new DukeException("Event command format error. Missing /from");
        }

        String[] splitData2 = splitData1[1].split(" /to ", 2);
        if (splitData2.length < 2) {
            throw new DukeException("Event command format error. Missing /to");
        }

        Task event = new Event(splitData1[0], splitData2[0], splitData2[1]);
        tasks.addTask(event);

        ui.showAddTask(event.toString(), tasks.size());
    }
}
