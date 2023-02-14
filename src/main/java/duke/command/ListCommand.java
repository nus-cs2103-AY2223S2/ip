package duke.command;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import duke.constant.DialogType;
import duke.constant.Message;
import duke.database.DukeRepo;
import duke.exception.DatabaseCloseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * ListCommand.
 *
 * @see Command
 */
public class ListCommand extends Command {

    private Optional<LocalDateTime> filterDate;
    private Optional<String> filterString;

    /**
     * Default constructor.
     */
    public ListCommand() {
        this.filterDate = Optional.empty();
        this.filterString = Optional.empty();
    }

    /**
     * Default constructor for filtering by date.
     *
     * @param filterDate date for filtering
     * @see LocalDateTime
     */
    public ListCommand(LocalDateTime filterDate) {
        this(Optional.of(filterDate), Optional.empty());
    }

    /**
     * Default constructor for filtering by keyword.
     *
     * @param filterString keyword for filtering
     */
    public ListCommand(String filterString) {
        this(Optional.empty(), Optional.of(filterString));
    }

    /**
     * Base constructor, internal use only to control the filtering option.
     * @param filterDate date for filtering
     * @param filterString keyword for filtering
     * @see LocalDateTime
     */
    private ListCommand(Optional<LocalDateTime> filterDate, Optional<String> filterString) {
        this.filterDate = filterDate;
        this.filterString = filterString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(DukeRepo db, BiConsumer<DialogType, String> con) {
        List<Task> filtered;
        StringBuilder sb = new StringBuilder();

        try {
            if (!filterDate.isEmpty()) {
                sb.append(Message.FIND_TASKS + "\n");
                filtered = filter(db, filterDate.get());
            } else if (!filterString.isEmpty()) {
                sb.append(Message.FIND_TASKS + "\n");
                filtered = filter(db, filterString.get());
            } else {
                filtered = db.getAllTask();

                if (filtered.isEmpty()) {
                    sb.append(Message.LIST_EMPTY + "\n");
                    con.accept(DialogType.WARNING, sb.toString());
                    return;
                } else {
                    sb.append(Message.LIST_TASKS + "\n");
                }
            }
        } catch (DatabaseCloseException e) {
            sb.append(e.getMessage());
            con.accept(DialogType.ERROR, sb.toString());
            return;
        }

        for (int i = 0; i < filtered.size(); i++) {
            sb.append(String.format("%d. %s", i + 1, filtered.get(i)) + "\n");
        }

        con.accept(DialogType.NORMAL, sb.toString());
    }

    /**
     * Filters task list by date.
     *
     * @param db {@link DukeRepo} a data layer interface object
     * @param filterDate date for filtering
     * @return a list of task
     * @throws DatabaseCloseException if data layer was closed
     * @see LocalDateTime
     */
    private List<Task> filter(DukeRepo db, LocalDateTime filterDate) throws DatabaseCloseException {
        return db.getAllTask().stream().filter(task -> {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                return d.getBy().toLocalDate().equals(filterDate.toLocalDate());
            }
            if (task instanceof Event) {
                Event e = (Event) task;
                return e.getFrom().toLocalDate().equals(filterDate.toLocalDate());
            }
            return false;
        }).collect(Collectors.toList());
    }

    /**
     * Filters task list by keyword.
     *
     * @param db {@link DukeRepo} a data layer interface object
     * @param filterString keyword for filtering
     * @return a list of task
     * @throws DatabaseCloseException if data layer was closed
     */
    private List<Task> filter(DukeRepo db, String filterString) throws DatabaseCloseException {
        return db.getAllTask().stream()
            .filter(task -> task.toString().toLowerCase().contains(filterString.toLowerCase()))
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
