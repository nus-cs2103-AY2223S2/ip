package duke.command;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import duke.constant.Message;
import duke.database.DukeRepo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

public class ListCommand extends Command {

    private Optional<LocalDateTime> filterDate;

    /**
     * Default constructor
     */
    public ListCommand() {
        this.filterDate = Optional.empty();
    }

    /**
     * Default constructor with filter option
     * 
     * @param filterDate
     */
    public ListCommand(LocalDateTime filterDate) {
        this.filterDate = Optional.of(filterDate);
    }

    /**
     * List all task from database and print the list.
     * 
     * @see Command#execute(DukeRepo, Ui)
     */
    @Override
    public void execute(DukeRepo db, Ui ui) {

        // filter the list if any
        List<Task> filtered;
        if (filterDate.isEmpty()) {
            filtered = db.getAllTask();
        } else {
            LocalDateTime key = filterDate.get();
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

        // prints the list
        ui.println(Message.LIST_TASKS);
        for (int i = 0; i < filtered.size(); i++) {
            ui.println(String.format("\t%d. %s", i + 1, filtered.get(i)));
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
