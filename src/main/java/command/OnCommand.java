package command;

import storage.TaskList;
import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class OnCommand extends Command {
    private LocalDate queryDate;

    /**
     * Constructor for a date query on command.
     * @param queryDate  deadline for task
     */
    public OnCommand(LocalDate queryDate) {
        this.queryDate = queryDate;
    }

    @Override
    public String run(TaskList taskList) {
        ArrayList<Task> taskOnQueryDate = new ArrayList<>(taskList.indexTask().stream().filter((task) ->
                (task instanceof Deadline && ((Deadline) task).daysToDeadline(this.queryDate) == 0) ||
                (task instanceof Event && ((Event) task).daysToEvent(this.queryDate) == 0))
                .collect(Collectors.toList()));

        if (taskOnQueryDate.size() == 0) {
            return "You have no task on " + this.queryDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "!";
        }

        String res = "There are " + taskOnQueryDate.size() + " tasks on " +
                this.queryDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":\n";

        for (int i = 0; i < taskOnQueryDate.size(); i++) {
            res += (i+1) + ". " + taskOnQueryDate.get(i) + "\n";
        }

        return res.trim();
    }
}
