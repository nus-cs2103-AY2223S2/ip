package duke.command;

import java.time.LocalDate;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Creates the Command class
 */
public abstract class Command {

    /**
     * Executes command.
     *
     * @param tasks   TaskList to be executed on.
     * @param ui      Ui to display the execution of command.
     * @param storage Storage to save TaskList.
     * @throws DukeException If TaskList cannot be saved or invalid index parsed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Creates the AddCommand class.
     */
    public static class AddCommand extends Command {
        /**
         * Task to be added
         */
        private final Task task;

        /**
         * Constructs AddCommand class.
         *
         * @param task Task to be added.
         */
        public AddCommand(Task task) {
            this.task = task;
        }

        /**
         * Gets task to be added.
         *
         * @return Task to be added.
         */
        public Task getTask() {
            return task;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            tasks.add(task);
            tasks.sort();
            storage.save(tasks);
            ui.showAdded(task);
            ui.showListSize(tasks);
        }
    }

    /**
     * Creates the ListCommand class.
     */
    public static class ListCommand extends Command {

        /**
         * Constructs ListCommand class.
         */
        public ListCommand() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showList(tasks);
        }
    }

    /**
     * Creates the MarkCommand class.
     */
    public static class MarkCommand extends Command {
        /**
         * Index of task to be marked
         */
        private final int index;

        /**
         * Constructs MarkCommand class.
         *
         * @param index Index of task to be marked.
         */
        public MarkCommand(int index) {
            this.index = index;
        }

        /**
         * Gets index of task to be marked.
         *
         * @return Index of task to be marked.
         */
        public int getIndex() {
            return index;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            Task task;
            try {
                assert index >= 0 : "Index must be non-negative.";
                task = tasks.get(index);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index entered!");
            }
            tasks.markTask(index);
            storage.save(tasks);
            ui.showMarked(task);
        }
    }

    /**
     * Creates the UnmarkCommand class.
     */
    public static class UnmarkCommand extends Command {
        /**
         * Index of task to be unmarked
         */
        private final int index;

        /**
         * Constructs UnmarkCommand class.
         *
         * @param index Index of task to be unmarked.
         */
        public UnmarkCommand(int index) {
            this.index = index;
        }

        /**
         * Gets index of task to be unmarked.
         *
         * @return Index of task to be unmarked.
         */
        public int getIndex() {
            return index;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            Task task;
            try {
                assert index >= 0 : "Index must be non-negative.";
                task = tasks.get(index);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index entered!");
            }
            tasks.unmarkTask(index);
            storage.save(tasks);
            ui.showUnmarked(task);
        }
    }

    /**
     * Creates the DeleteCommand class.
     */
    public static class DeleteCommand extends Command {
        /**
         * Index of task to be deleted
         */
        private final int index;

        /**
         * Constructs DeleteCommand class.
         *
         * @param index Index of task to be deleted.
         */
        public DeleteCommand(int index) {
            this.index = index;
        }

        /**
         * Gets index of task to be deleted.
         *
         * @return Index of task to be deleted.
         */
        public int getIndex() {
            return index;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            Task task;
            try {
                assert index >= 0 : "Index must be non-negative.";
                task = tasks.get(index);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index entered!");
            }
            tasks.remove(index);
            storage.save(tasks);
            ui.showDeleted(task);
            ui.showListSize(tasks);
        }
    }

    /**
     * Creates the FilterCommand class.
     */
    public static class FilterCommand extends Command {
        /**
         * Keywords for TaskList to be filtered by
         */
        private final String[] keywords;

        /**
         * Constructs FilterCommand class.
         *
         * @param keywords Keywords for TaskList to be filtered by.
         */
        public FilterCommand(String... keywords) {
            this.keywords = keywords;
        }

        /**
         * Gets keywords for TaskList to be filtered by.
         *
         * @return Keywords for TaskList to be filtered by.
         */
        public String[] getKeywords() {
            return keywords;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            TaskList filteredTasks = tasks.filter(keywords);
            ui.showFilteredByKeywords(keywords, filteredTasks);
        }
    }

    /**
     * Creates the FilterDateCommand class.
     */
    public static class FilterDateCommand extends Command {
        /**
         * Dates for TaskList to be filtered by
         */
        private final LocalDate[] dates;

        /**
         * Constructs FilterDateCommand class.
         *
         * @param dates Dates for TaskList to be filtered by.
         */
        public FilterDateCommand(LocalDate... dates) {
            this.dates = dates;
        }

        /**
         * Gets dates for TaskList to be filtered by.
         *
         * @return Dates for TaskList to be filtered by.
         */
        public LocalDate[] getDates() {
            return dates;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            TaskList filteredTasks = tasks.filterDate(dates);
            ui.showFilteredByDates(dates, filteredTasks);
        }
    }

    /**
     * Creates the SortCommand class.
     */
    public static class SortCommand extends Command {

        /**
         * Constructs SortCommand class.
         */
        public SortCommand() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            tasks.sort();
            ui.showList(tasks);
        }
    }

    /**
     * Creates the SortDateCommand class.
     */
    public static class SortDateCommand extends Command {

        /**
         * Constructs SortDateCommand class.
         */
        public SortDateCommand() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            tasks.sortDate();
            ui.showList(tasks);
        }
    }

    /**
     * Creates the SortTaskCommand class.
     */
    public static class SortTaskCommand extends Command {

        /**
         * Constructs SortTaskCommand class.
         */
        public SortTaskCommand() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            tasks.sortTask();
            ui.showList(tasks);
        }
    }

    /**
     * Creates the SortDoneCommand class.
     */
    public static class SortDoneCommand extends Command {

        /**
         * Constructs SortDoneCommand class.
         */
        public SortDoneCommand() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            tasks.sortDone();
            ui.showList(tasks);
        }
    }

    /**
     * Creates the HelpCommand class.
     */
    public static class HelpCommand extends Command {

        /**
         * Constructs SortDoneCommand class.
         */
        public HelpCommand() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showHelp();
        }
    }

    /**
     * Creates the ExitCommand class.
     */
    public static class ExitCommand extends Command {

        /**
         * Constructs ExitCommand class.
         */
        public ExitCommand() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showExit();
        }
    }
}
