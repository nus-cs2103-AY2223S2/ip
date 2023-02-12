package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public abstract class Command {
    /** Checks if command exits Duke */
    private static boolean isExit;

    /**
     * Executes command.
     *
     * @param tasks TaskList to be executed on.
     * @param ui Ui to display the execution of command.
     * @param storage Storage to save TaskList.
     * @throws DukeException If TaskList cannot be saved or invalid index parsed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public static class AddCommand extends Command {
        /** Task to be added */
        private Task task;

        /**
         * Constructs AddCommand class.
         *
         * @param task Task to be added.
         */
        public AddCommand(Task task) {
            this.task = task;
            Command.isExit =false;
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

    public static class ListCommand extends Command {

        /**
         * Constructs ListCommand class.
         */
        public ListCommand() {
            Command.isExit =false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showList(tasks);
        }
    }

    public static class MarkCommand extends Command {
        /** Index of task to be marked */
        private int index;

        /**
         * Constructs MarkCommand class.
         *
         * @param index Index of task to be marked.
         */
        public MarkCommand(int index) {
            this.index = index;
            Command.isExit =false;
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
                assert index >= 0: "Index must be non-negative.";
                task = tasks.get(index);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index entered!");
            }
            tasks.markTask(index);
            storage.save(tasks);
            ui.showMarked(task);
        }
    }

    public static class UnmarkCommand extends Command {
        /** Index of task to be unmarked */
        private int index;

        /**
         * Constructs UnmarkCommand class.
         *
         * @param index Index of task to be unmarked.
         */
        public UnmarkCommand(int index) {
            this.index = index;
            Command.isExit =false;
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
                assert index >= 0: "Index must be non-negative.";
                task = tasks.get(index);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index entered!");
            }
            tasks.unmarkTask(index);
            storage.save(tasks);
            ui.showUnmarked(task);
        }
    }

    public static class DeleteCommand extends Command {
        /** Index of task to be deleted */
        private int index;

        /**
         * Constructs DeleteCommand class.
         *
         * @param index Index of task to be deleted.
         */
        public DeleteCommand(int index) {
            this.index = index;
            Command.isExit =false;
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
                assert index >= 0: "Index must be non-negative.";
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

    public static class FilterCommand extends Command {
        /** Keywords for TaskList to be filtered by */
        private String[] keywords;

        /**
         * Constructs FilterCommand class.
         *
         * @param keywords Keywords for TaskList to be filtered by.
         */
        public FilterCommand(String... keywords) {
            this.keywords = keywords;
            Command.isExit =false;
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

    public static class FilterDateCommand extends Command {
        /** Dates for TaskList to be filtered by */
        private LocalDate[] dates;

        /**
         * Constructs FilterDateCommand class.
         *
         * @param dates Dates for TaskList to be filtered by.
         */
        public FilterDateCommand(LocalDate... dates) {
            this.dates = dates;
            Command.isExit =false;
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

    public static class SortCommand extends Command {

        /**
         * Constructs SortCommand class.
         */
        public SortCommand() {
            Command.isExit =false;
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

    public static class SortDateCommand extends Command {

        /**
         * Constructs SortDateCommand class.
         */
        public SortDateCommand() {
            Command.isExit =false;
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

    public static class SortTaskCommand extends Command {

        /**
         * Constructs SortTaskCommand class.
         */
        public SortTaskCommand() {
            Command.isExit =false;
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

    public static class SortDoneCommand extends Command {

        /**
         * Constructs SortDoneCommand class.
         */
        public SortDoneCommand() {
            Command.isExit =false;
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

    public static class HelpCommand extends Command {

        /**
         * Constructs SortDoneCommand class.
         */
        public HelpCommand() {
            Command.isExit =false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showHelp();
        }
    }

    public static class ExitCommand extends Command {

        /**
         * Constructs ExitCommand class.
         */
        public ExitCommand() {
            Command.isExit = true;
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
