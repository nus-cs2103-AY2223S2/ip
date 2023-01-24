package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;

import java.time.LocalDate;

public abstract class Command {
    private static boolean isExit;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public static class AddCommand extends Command {
        private Task task;

        public AddCommand(Task task) {
            this.task = task;
            Command.isExit =false;
        }

        public Task getTask() {
            return task;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
            tasks.addTask(task);
            storage.save(tasks);
            ui.showAdded(task);
            ui.showListSize(tasks);
        }
    }

    public static class ListCommand extends Command {

        public ListCommand() {
            Command.isExit =false;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            tasks.showList(ui);
        }
    }

    public static class MarkCommand extends Command {
        private int index;

        public MarkCommand(int index) {
            this.index = index;
            Command.isExit =false;
        }

        public int getIndex() {
            return index;
        }

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
        private int index;

        public UnmarkCommand(int index) {
            this.index = index;
            Command.isExit =false;
        }

        public int getIndex() {
            return index;
        }

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
        private int index;

        public DeleteCommand(int index) {
            this.index = index;
            Command.isExit =false;
        }

        public int getIndex() {
            return index;
        }

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
        private Object object;

        public FilterCommand(Object obj) {
            this.object = obj;
            Command.isExit =false;
        }

        public Object getObject() {
            return object;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            if (object instanceof LocalDate) {
                LocalDate date = (LocalDate) object;
                tasks.filterTasksByDate(ui, date);
            }
        }
    }

    public static class ExitCommand extends Command {

        public ExitCommand() {
            Command.isExit = true;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.closeScanner();
            ui.showExit();
        }
    }

    public boolean isExit() {
        return this.isExit;
    }
}