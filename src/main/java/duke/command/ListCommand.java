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
    private Optional<String> filterString;

    /**
     * Default constructor
     */
    public ListCommand() {
        this.filterDate = Optional.empty();
        this.filterString = Optional.empty();
    }

    public ListCommand(LocalDateTime filterDate) {
        this(Optional.of(filterDate), Optional.empty());
    }

    public ListCommand(String filterString) {
        this(Optional.empty(), Optional.of(filterString));
    }

    public ListCommand(Optional<LocalDateTime> filterDate, Optional<String> filterString) {
        this.filterDate = filterDate;
        this.filterString = filterString;
    }

    /**
     * List all task from database and print the list.
     * 
     * @see Command#execute(DukeRepo, Ui)
     */
    @Override
    public void execute(DukeRepo db, Ui ui) {

        List<Task> filtered;
        if (filterDate.isEmpty() && filterString.isEmpty()) {
            filtered = db.getAllTask();
        } else {

            filtered = db.getAllTask().stream().filter(task -> {
                return filterDate.map(x -> {
                    if (task instanceof Deadline) {
                        Deadline d = (Deadline) task;
                        if (d.getBy().toLocalDate().equals(x.toLocalDate())) {
                            return true;
                        }
                    }
                    if (task instanceof Event) {
                        Event e = (Event) task;
                        if (e.getFrom().toLocalDate().equals(x.toLocalDate())) {
                            return true;
                        }
                    }
                    return false;
                }).orElse(true);
            }).filter(task -> filterString.map(x -> task.toString().contains(x)).orElse(true))
                    .collect(Collectors.toList());
        }

        if (filtered.size() > 0) {
            if (!filterDate.isEmpty() || !filterString.isEmpty()) {
                ui.println(Message.FIND_TASKS);
            } else {
                ui.println(Message.LIST_TASKS);
            }

            for (int i = 0; i < filtered.size(); i++) {
                ui.println(String.format("\t%d. %s", i + 1, filtered.get(i)));
            }
        } else {
            ui.println(Message.LIST_EMPTY);
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
