import java.time.LocalDate;

public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";
    private String desc;
    private LocalDate from;
    private  LocalDate to;

    public EventCommand(String desc,LocalDate from,LocalDate to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    public void execute(TaskList tasks,Ui ui,Storage storage) {
        Event newTask = new Event(desc,from,to);
        tasks.addTask(newTask);
    }
}
