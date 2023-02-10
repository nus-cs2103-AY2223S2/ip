package duke.command;

import duke.Deadline;
import duke.DukeResponse;
import duke.MessageGenerator;
import duke.TaskList;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command{
    String taskDesc;
    LocalDateTime by;
    Boolean hasTime;
    TaskList taskList;

    public DeadlineCommand(String taskDesc, LocalDateTime by, Boolean hasTime, TaskList taskList) {
        this.taskDesc = taskDesc;
        this.by = by;
        this.hasTime = hasTime;
        this.taskList = taskList;
    }


    @Override
    public DukeResponse execute() {
        Deadline deadline = new Deadline(taskDesc, by, hasTime);
        taskList.add(deadline);
        return new DukeResponse(MessageGenerator.genAddedTaskMsg("deadline"));
    }
}
