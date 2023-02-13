package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DateTimeParser;
import duke.Deadline;
import duke.DukeResponse;
import duke.MessageGenerator;
import duke.TaskList;

/**
 * A command that when executed adds a deadline task to the list of tasks.
 */
public class DeadlineCommand extends Command {
    private String taskDesc;
    private LocalDateTime by;
    private Boolean hasTime;
    private TaskList taskList;

    /**
     * Constructs a deadline command with the given arguments.
     *
     * @param taskDesc
     * @param by
     * @param hasTime
     * @param taskList
     */
    public DeadlineCommand(String taskDesc, LocalDateTime by, Boolean hasTime, TaskList taskList) {
        this.taskDesc = taskDesc;
        this.by = by;
        this.hasTime = hasTime;
        this.taskList = taskList;
    }

    /**
     * Creates a deadline command with the given arguments.
     *
     * @param taskDesc
     * @param byString
     * @param taskList
     * @return the created deadline command
     */
    public static Command create(String taskDesc, String byString, TaskList taskList) {
        try {
            LocalDateTime by = DateTimeParser.parse(byString);
            String[] parts = byString.split(" ");
            boolean hasTime = parts.length == 2;
            return new DeadlineCommand(taskDesc, by, hasTime, taskList);

        } catch (DateTimeParseException e) {
            return new ErrorCommand(MessageGenerator.genDateTimeParseErrorMsg());
        }
    }


    @Override
    public DukeResponse execute() {
        assert taskDesc != null;
        assert by != null;
        assert hasTime != null;
        assert taskList != null;

        Deadline deadline = new Deadline(taskDesc, by, hasTime);
        taskList.add(deadline);
        return new DukeResponse(MessageGenerator.genAddedTaskMsg("deadline"));
    }
}
