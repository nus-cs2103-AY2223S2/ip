package duke.command;


import duke.TaskList;
import duke.DateTimeParser;
import duke.MessageGenerator;
import duke.DukeResponse;
import duke.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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
