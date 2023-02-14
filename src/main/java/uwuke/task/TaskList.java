package uwuke.task;

import java.security.DrbgParameters.Capability;
import java.util.ArrayList;

import uwuke.input.Parser;
import uwuke.output.DukeException;
import uwuke.output.Printer;

/**
 * Encapsulates an ArrayList to help with task operations
 */
public class TaskList {

    private static final int CAPACITY = 100;
    private ArrayList<Task> tasks;

    public TaskList(int capacity) {
        this.tasks = new ArrayList<>(capacity);
    }
    
    public TaskList() {
        this.tasks = new ArrayList<>(CAPACITY);
    }

    private boolean isValidIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            Printer.printWithDecorations("Index out of bounds!");
            return false;
        } else if (tasks.get(index) == null) {
            Printer.printWithDecorations("Task not initialised!");
            return false;
        } else {
            return true;
        }
    }

    public void addDeadline(String input) throws DukeException {
        if (tasks.size() >= CAPACITY) {
            Printer.printNotEnoughSpace();
            return;
        }
        String[] dt = Parser.parseDeadline(input);
        Deadline dl = new Deadline(dt[0], dt[1]);
        tasks.add(dl);
        Printer.printAddedConfirmation(dl, tasks.size());
        assert tasks.size() <= CAPACITY : "Number of tasks should not exceed specified maximum!";
    }

    public void addEvent(String input) throws DukeException {
        if (tasks.size() >= CAPACITY) {
            Printer.printNotEnoughSpace();
            return;
        }
        String[] et = Parser.parseEvent(input);
        Event e = new Event(et[0], et[1], et[2]);
        tasks.add(e);
        Printer.printAddedConfirmation(e, tasks.size());
        assert tasks.size() <= CAPACITY : "Number of tasks should not exceed specified maximum!";
    }

    public void addTodo(String input) throws DukeException {
        if (tasks.size() >= CAPACITY) {
            Printer.printNotEnoughSpace();
            return;
        }

        Todo td = new Todo(Parser.parseToDo(input));
        tasks.add(td);
        Printer.printAddedConfirmation(td, tasks.size());
        assert tasks.size() <= CAPACITY : "Number of tasks should not exceed specified maximum!";
    }

    public void markTask(String input) throws DukeException {
        int markIndex = Parser.parseMark(input);
        if (isValidIndex(markIndex)) // Note that this check also prints out error messages if any
            Printer.printWithDecorations(tasks.get(markIndex).markDone());
    }

    public void unmarkTask(String input) throws DukeException {
        int unmarkIndex = Parser.parseUnmark(input);
        if (isValidIndex(unmarkIndex)) // Note that this check also prints out error messages if any
            Printer.printWithDecorations(tasks.get(unmarkIndex).unmarkDone());
    }

    public void deleteTask(String input) throws DukeException {
        int deleteIndex = Parser.parseDelete(input);
        if (isValidIndex(deleteIndex)) {
            Task removedTask = tasks.get(deleteIndex);
            tasks.remove(deleteIndex);
            Printer.printDeleteConfirmation(removedTask, tasks.size());
        }
        assert tasks.size() <= CAPACITY : "Number of tasks should not exceed specified maximum!";
    }

    public void addTask(Task task) {
        tasks.add(task);
        assert tasks.size() <= CAPACITY : "Number of tasks should not exceed specified maximum!";
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public void findTask(String input) throws DukeException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String keyword = Parser.parseFind(input);
        for (Task t : tasks) {
            if (t.toString().contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        Printer.printFindResults(matchingTasks);
    }
}
