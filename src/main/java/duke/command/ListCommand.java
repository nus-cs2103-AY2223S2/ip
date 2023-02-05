package duke.command;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import duke.constant.Message;
import duke.database.DukeRepo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

/**
 * ListCommand
 */
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

    /**
     * Default for filtering by date
     */
    public ListCommand(LocalDateTime filterDate) {
        this(Optional.of(filterDate), Optional.empty());
    }

    /**
     * Default for filtering by keyword
     */
    public ListCommand(String filterString) {
        this(Optional.empty(), Optional.of(filterString));
    }

    /**
     * Base Constructor
     */
    public ListCommand(Optional<LocalDateTime> filterDate, Optional<String> filterString) {
        this.filterDate = filterDate;
        this.filterString = filterString;
    }

    /**
     * List all task from database and print the list.
     *
     * {@inheritDoc}
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
                        return d.getBy().toLocalDate().equals(x.toLocalDate());
                    }
                    if (task instanceof Event) {
                        Event e = (Event) task;
                        return e.getFrom().toLocalDate().equals(x.toLocalDate());
                    }
                    return false;
                }).orElse(true);
            }).filter(task -> filterString.map(x -> task.toString().contains(x)).orElse(true))
                    .collect(Collectors.toList());
        }

        if (filtered.size() > 0) {
            if (!filterDate.isEmpty() || !filterString.isEmpty()) {
                ui.printConsole(Message.FIND_TASKS);
            } else {
                ui.printConsole(Message.LIST_TASKS);
            }

            for (int i = 0; i < filtered.size(); i++) {
                ui.printConsole(String.format("\t%d. %s", i + 1, filtered.get(i)));
            }
        } else {
            ui.printConsole(Message.LIST_EMPTY);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(DukeRepo db, Consumer<String> con) {
        List<Task> filtered;
        StringBuilder sb = new StringBuilder();

        if (!filterDate.isEmpty()) {
            sb.append(Message.FIND_TASKS + "\n");
            filtered = filter(db, filterDate.get());
        } else if (!filterString.isEmpty()) {
            sb.append(Message.FIND_TASKS + "\n");
            filtered = filter(db, filterString.get());
        } else {
            sb.append(Message.LIST_TASKS + "\n");
            filtered = db.getAllTask();
        }

        for (int i = 0; i < filtered.size(); i++) {
            sb.append(String.format("\t%d. %s", i + 1, filtered.get(i)) + "\n");
        }

        con.accept(sb.toString());
    }

    /**
     * Filters task list by date.
     *
     * @param db
     * @param date
     * @return
     */
    private List<Task> filter(DukeRepo db, LocalDateTime date) {
        return db.getAllTask().stream().filter(task -> {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                return d.getBy().toLocalDate().equals(date.toLocalDate());
            }
            if (task instanceof Event) {
                Event e = (Event) task;
                return e.getFrom().toLocalDate().equals(date.toLocalDate());
            }
            return false;
        }).collect(Collectors.toList());
    }

    /**
     * Filters task list by keyword.
     *
     * @param db
     * @param keyword
     * @return
     */
    private List<Task> filter(DukeRepo db, String keyword) {
        return db.getAllTask().stream()
            .filter(task -> task.toString().contains(keyword))
            .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
