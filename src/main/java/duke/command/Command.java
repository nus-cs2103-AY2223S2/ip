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
            tasks.addTask(task);
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
            tasks.showList(ui);
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
                task = tasks.getTask(index);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index entered!");
            }
            tasks.markTask(task);
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
                task = tasks.getTask(index);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index entered!");
            }
            tasks.unmarkTask(task);
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
                task = tasks.getTask(index);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid index entered!");
            }
            tasks.deleteTask(index);
            storage.save(tasks);
            ui.showDeleted(task);
            ui.showListSize(tasks);
        }
    }

    public static class FilterCommand extends Command {
        /** Object for TaskList to be filtered by */
        private Object object;

        /**
         * Constructs FilterCommand class.
         *
         * @param obj Object for TaskList to be filtered by.
         */
        public FilterCommand(Object obj) {
            this.object = obj;
            Command.isExit =false;
        }

        /**
         * Gets object for TaskList to be filtered by.
         *
         * @return Object for TaskList to be filtered by.
         */
        public Object getObject() {
            return object;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            if (object instanceof LocalDate) {
                LocalDate date = (LocalDate) object;
                tasks.filterDate(ui, date);
            }
            if (object instanceof String) {
                String keyword = (String) object;
                tasks.filter(ui, keyword);
            }
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

    /**
     * Checks whether to exit Duke.
     *
     * @return Status of whether to exit Duke.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
