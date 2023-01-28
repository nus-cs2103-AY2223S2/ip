import java.time.LocalDate;

public class AddCommand extends Command{

    private String type;

    private String description;

    private String by;

    private String from;

    private String to;

    public AddCommand(String description) {
        super();
        this.description = description;
        this.type = "T";
    }

    public AddCommand(String description, String by) {
        super();
        this.description = description;
        this.by = by;
        this.type = "D";
    }

    public AddCommand(String description, String from, String to) {
        super();
        this.description = description;
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        switch (this.type) {
            case "E":
                tasks.addEvent(description, from, to);
                ui.showTask(new Event(description, from, to), tasks.size());
                break;
            case "D":
                tasks.addDeadline(description, by);
                ui.showTask(new Deadline(description, LocalDate.parse(by)), tasks.size());
                break;
            case "T":
                tasks.addTodo(description);
                ui.showTask(new Todo(description), tasks.size());
                break;
        }
        ui.showLine();
    }
}
