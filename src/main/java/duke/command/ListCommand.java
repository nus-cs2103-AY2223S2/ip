package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import duke.constant.Message;
import duke.database.DukeRepo;
import duke.exception.InvalidCommandArgsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;
import duke.utils.DateUtil;

public class ListCommand extends Command {

    // Todo: change to localdatetime type
    private String filterDate;

    /**
     * Default constructor
     */
    public ListCommand() {
        this.filterDate = "";
    }

    /**
     * Default constructor with filter option
     * 
     * @param filterDate
     */
    public ListCommand(String filterDate) {
        this.filterDate = filterDate;
    }

    /**
     * List all task from database and print the list.
     * 
     * @see Command#execute(DukeRepo, Ui)
     */
    @Override
    public void execute(DukeRepo db, Ui ui) throws InvalidCommandArgsException {
        try {
            List<Task> filtered;
            if (filterDate.isBlank()) {
                filtered = db.getAllTask();
            } else {
                LocalDateTime key = DateUtil.toLocalDateTime(filterDate);
                filtered = db.getAllTask().stream().filter(task -> {
                    if (task instanceof Deadline) {
                        Deadline d = (Deadline) task;
                        if (d.getBy().toLocalDate().equals(key.toLocalDate())) {
                            return true;
                        }
                    }
                    if (task instanceof Event) {
                        Event e = (Event) task;
                        if (e.getFrom().toLocalDate().equals(key.toLocalDate())) {
                            return true;
                        }
                    }
                    return false;
                }).collect(Collectors.toList());
            }

            ui.println(Message.LIST_TASKS);
            for (int i = 0; i < filtered.size(); i++) {
                ui.println(String.format("\t%d. %s", i + 1, filtered.get(i)));
            }
        } catch (DateTimeParseException e) {
            throw new InvalidCommandArgsException(Message.EXCEPTION_INVALID_DATE_FORMAT);
        }

    }

    /**
     * @see Command#isExit()
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
