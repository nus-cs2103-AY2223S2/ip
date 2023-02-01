import java.time.LocalDate;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private String desc;
    private LocalDate byWhen;

    public DeadlineCommand(String desc,LocalDate byWhen) {
        this.desc = desc;
        this.byWhen = byWhen;
    }

    public void execute(TaskList tasks,Ui ui,Storage storage) {
            Deadline newTask = new Deadline(desc,byWhen);
            tasks.addTask(newTask);
    }

}
