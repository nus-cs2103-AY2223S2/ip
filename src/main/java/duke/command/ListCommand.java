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
     * @param db
     * @param date
     * @return
     * @throws DatabaseCloseException
     */
    private List<Task> filter(DukeRepo db, LocalDateTime date) throws DatabaseCloseException {
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
     * @throws DatabaseCloseException
     */
    private List<Task> filter(DukeRepo db, String keyword) throws DatabaseCloseException {
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
