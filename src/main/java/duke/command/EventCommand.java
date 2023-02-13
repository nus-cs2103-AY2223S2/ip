package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DateTimeParser;
import duke.DukeResponse;
import duke.Event;
import duke.MessageGenerator;
import duke.TaskList;

/**
 * Represents a command that when executed adds an event to the given task list.
 */
public class EventCommand extends Command {
    private String taskDesc;
    private LocalDateTime from;
    private Boolean fromHasTime;
    private LocalDateTime to;
    private Boolean toHasTime;
    private TaskList taskList;

    /**
     * Constructs an EventCommand with the given arguments.
     *
     * @param taskDesc
     * @param from
     * @param fromHasTime
     * @param to
     * @param toHasTime
     * @param taskList
     */
    public EventCommand(String taskDesc, LocalDateTime from, Boolean fromHasTime, LocalDateTime to, Boolean toHasTime,
                        TaskList taskList) {
        this.taskDesc = taskDesc;
        this.from = from;
        this.fromHasTime = fromHasTime;
        this.to = to;
        this.toHasTime = toHasTime;
        this.taskList = taskList;
    }

    /**
     * Creates an EventCommand with the given arguments.
     *
     * @param taskDesc
     * @param fromString
     * @param toString
     * @param taskList
     * @return
     */
    public static Command create(String taskDesc, String fromString, String toString, TaskList taskList) {
        try {
            LocalDateTime from = DateTimeParser.parse(fromString);
            LocalDateTime to = DateTimeParser.parse(toString);

            String[] fromParts = fromString.split(" ");
            boolean fromHasTime = fromParts.length == 2;

            String[] toParts = toString.split(" ");
            boolean toHasTime = toParts.length == 2;

            assert taskDesc != null : "taskDesc should not be null";
            assert from != null : "/from field should not be null.";
            assert to != null : "/to field should not be null.";

            return new EventCommand(taskDesc, from, fromHasTime, to, toHasTime, taskList);

        } catch (DateTimeParseException e) {
            return new ErrorCommand(MessageGenerator.genDateTimeParseErrorMsg());
        }
    }

    @Override
    public DukeResponse execute() {
        assert taskDesc != null;
        assert from != null;
        assert fromHasTime != null;
        assert toHasTime != null;
        Event event = new Event(taskDesc, from, fromHasTime, to, toHasTime);
        taskList.add(event);
        return new DukeResponse(MessageGenerator.genAddedTaskMsg("event"));
    }
}
