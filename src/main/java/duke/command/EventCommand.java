package duke.command;

import duke.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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

    public static Command create(String taskDesc, String fromString, String toString, TaskList taskList) {
        try {
            LocalDateTime from = DateTimeParser.parse(fromString);
            LocalDateTime to = DateTimeParser.parse(toString);

            String[] fromParts = fromString.split(" ");
            boolean fromHasTime = fromParts.length == 2;

            String[] toParts = toString.split(" ");
            boolean toHasTime = toParts.length == 2;

            return new EventCommand(taskDesc, from, fromHasTime, to, toHasTime, taskList);

        } catch (DateTimeParseException e) {
            return new ErrorCommand(MessageGenerator.genDateTimeParseErrorMsg());
        }
    }

    @Override
    public DukeResponse execute() {
        Event event = new Event(taskDesc, from, fromHasTime, to, toHasTime);
        taskList.add(event);
        return new DukeResponse(MessageGenerator.genAddedTaskMsg("event"));
    }
}
