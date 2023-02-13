package duke.command;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DateTimeParser;
import duke.Deadline;
import duke.DukeResponse;
import duke.Event;
import duke.MessageGenerator;
import duke.Task;
import duke.TaskList;

/**
 * Represents a command that when executed, edits a single field of an existing task.
 */
public class EditCommand extends Command {
    private TaskList taskList;
    private String field;
    private Integer taskIdx;
    private String newValue;

    /**
     * Constructs an edit command with the given arguments.
     *
     * @param taskIdx
     * @param field
     * @param newValue
     * @param taskList
     */
    public EditCommand(Integer taskIdx, String field, String newValue, TaskList taskList) {
        this.field = field;
        this.taskIdx = taskIdx;
        this.taskList = taskList;
        this.newValue = newValue;
    }

    @Override
    public DukeResponse execute() {
        assert field != null;
        assert taskIdx != null;
        assert taskList != null;

        if (field.equals("/by")) {
            return handleBy();
        }

        if (field.equals("/from") || field.equals("/to")) {
            return handleFromOrTo();
        }

        if (field.equals("/desc")) {
            return handleDesc();
        }
        assert false;
        return new DukeResponse("Error");
    }

    private DukeResponse handleBy() {
        Task task = taskList.get(taskIdx);

        if (!(task instanceof Deadline)) {
            return new DukeResponse(MessageGenerator.genInvalidFieldMsg(field));
        }

        Deadline deadline = (Deadline) task;

        try {
            LocalDateTime by = DateTimeParser.parse(newValue);
            String[] parts = newValue.split(" ");
            boolean hasTime = parts.length == 2;
            deadline.setBy(by, hasTime);
            return new DukeResponse(MessageGenerator.genEditTaskMsg(deadline.toString()));

        } catch (DateTimeParseException e) {
            return new DukeResponse(MessageGenerator.genDateTimeParseErrorMsg());
        } catch (DateTimeException e) {
            return new DukeResponse(MessageGenerator.genDateTimeParseErrorMsg());
        }

    }

    private DukeResponse handleFromOrTo() {
        Task task = taskList.get(taskIdx);

        if (!(task instanceof Event)) {
            return new DukeResponse(MessageGenerator.genInvalidFieldMsg(field));
        }

        Event event = (Event) task;

        try {
            LocalDateTime dateTime = DateTimeParser.parse(newValue);
            String[] parts = newValue.split(" ");
            boolean hasTime = parts.length == 2;

            if (field.equals("/from")) {
                event.setFrom(dateTime, hasTime);
            }

            if (field.equals("/to")) {
                event.setTo(dateTime, hasTime);
            }

            return new DukeResponse(MessageGenerator.genEditTaskMsg(event.toString()));

        } catch (DateTimeParseException e) {
            return new DukeResponse(MessageGenerator.genDateTimeParseErrorMsg());
        } catch (DateTimeException e) {
            return new DukeResponse(MessageGenerator.genDateTimeParseErrorMsg());
        }
    }


    private DukeResponse handleDesc() {
        Task task = taskList.get(taskIdx);
        assert task != null;
        task.setDescription(newValue);
        return new DukeResponse(MessageGenerator.genEditTaskMsg(task.toString()));
    }

}
