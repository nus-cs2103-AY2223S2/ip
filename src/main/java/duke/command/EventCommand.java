package duke.command;

import duke.DukeResponse;
import duke.Event;
import duke.MessageGenerator;
import duke.TaskList;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    String taskDesc;
    LocalDateTime from;
    Boolean fromHasTime;
    LocalDateTime to;
    Boolean toHasTime;
    TaskList taskList;

    public EventCommand(String taskDesc, LocalDateTime from, Boolean fromHasTime, LocalDateTime to, Boolean toHasTime,
                        TaskList taskList) {
        this.taskDesc = taskDesc;
        this.from = from;
        this.fromHasTime = fromHasTime;
        this.to = to;
        this.toHasTime = toHasTime;
        this.taskList = taskList;
    }


    @Override
    public DukeResponse execute() {
        Event event = new Event(taskDesc, from, fromHasTime, to, toHasTime);
        taskList.add(event);
        return new DukeResponse(MessageGenerator.genAddedTaskMsg("event"));
    }
}
