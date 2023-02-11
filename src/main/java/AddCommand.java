import java.time.LocalDateTime;
public class AddCommand extends Command {
    protected  String name;
    protected LocalDateTime by;
    protected LocalDateTime from;
    protected LocalDateTime to;

    public AddCommand(String commandName, String name, LocalDateTime by, LocalDateTime from, LocalDateTime to) {
        super(commandName);
        this.name = name;
        switch (this.commandName) {
            case "deadline": {
                this.by = by;
                break;
            }
            case "event": {
                this.from = from;
                this.to = to;
                break;
            }
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        if (this.commandName.equals("todo")) {
            Todo t = new Todo(this.name);
            ui.addTodo(t);
            tasks.add(t);
        } else if (this.commandName.equals("deadline")) {
            Deadline d = new Deadline(this.name, this.by);
            ui.addDeadline(d);
            tasks.add(d);
        } else if (this.commandName.equals("event")) {
            Event e = new Event(this.name, this.from, this.to);
            ui.addEvent(e);
            tasks.add(e);
        }
        ui.showCurrentTaskNo(tasks);
    }
}
