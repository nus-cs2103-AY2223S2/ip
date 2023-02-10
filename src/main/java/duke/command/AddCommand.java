package duke.command;

import duke.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.io.IOException;
import java.time.LocalDate;

public class AddCommand extends Command{

    private TaskType t;
    private String desc;
    private boolean isDone;
    private LocalDate deadlineBy;
    private String eventFrom;
    private String eventTo;

    public AddCommand(TaskType type, String desc, boolean isDone) {
        this.t = type;
        this.desc = desc;
        this.isDone = isDone;
    }

    public AddCommand(TaskType type, String desc, boolean isDone, LocalDate deadlineBy) {
        this.t = type;
        this.desc = desc;
        this.isDone = isDone;
        this.deadlineBy = deadlineBy;
    }

    public AddCommand(TaskType type, String desc, boolean isDone, String eventFrom, String eventTo) {
        this.t = type;
        this.desc = desc;
        this.isDone = isDone;
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    public String execute(TaskList l, Ui ui, Storage s, Command prevCommand, Duke duke) {
        String errorMsg = saveToFile(s, l, ui, prevCommand);

        assert(t == TaskType.ToDo || t == TaskType.Deadline || t == TaskType.Event);

        if (t == TaskType.ToDo) {
            l.add(new ToDo(desc, isDone));
        } else if (t == TaskType.Deadline) {
            l.add(new Deadline(desc, isDone, deadlineBy));
        } else if (t == TaskType.Event) {
            l.add(new Event(desc, isDone, eventFrom, eventTo));
        } //CODE QUALITY

        return fullReplyMsg(errorMsg, ui.showAdded(l));
    }

}
