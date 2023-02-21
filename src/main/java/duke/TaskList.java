package duke;

import duke.exceptions.DukeyException;

import static java.lang.Integer.parseInt;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;


public class TaskList {
    private ArrayList<Task> list;
    private Ui ui;

    public TaskList(Ui ui) {
        this.list = new ArrayList<>();
        this.ui = ui;
    }

    public TaskList() {
        this.list = new ArrayList<>();
        this.ui = new Ui();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public Iterator<Task> getIterator() {
        return this.list.iterator();
    }

    public void addTask(Task task) {
        this.list.add(task);
        ui.printAddedMessage(task);
        printSize();
    }

    public void addTaskFromSave(Task task) {
        this.list.add(task);
    }

    public void readList() {
        ui.readList(this.list);
    }

    public void mark() throws DukeyException {
        String taskNumberString = ui.getTaskNumber();
        int taskNumber;
        try {
            taskNumber = parseInt(taskNumberString.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeyException("Error! Invalid task number format!");
        }

        if (taskNumber < 0) {
            throw new DukeyException("Error! Invalid task number");
        } else if (taskNumber >= this.list.size()) {
            throw new DukeyException("Error! DukeyList only contains " + this.list.size() + " tasks");
        }

        Task taskToMark = this.list.get(taskNumber);
        taskToMark.markAsDone();
        ui.printMarkedMessage(taskNumber, taskToMark);
    }

    public void unmark() throws DukeyException {
        String taskNumberString = ui.getTaskNumber();
        int taskNumber;
        try {
            taskNumber = parseInt(taskNumberString.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeyException("Error! Invalid task number format!");
        }

        if (taskNumber < 0) {
            throw new DukeyException("Error! Invalid task number");
        } else if (taskNumber >= this.list.size()) {
            throw new DukeyException("Error! DukeyList only contains " + this.list.size() + " tasks");
        }
        Task taskToUnmark = this.list.get(taskNumber);
        taskToUnmark.unmark();
        ui.printUnmarkedMessage(taskNumber, taskToUnmark);
    }

    public void delete() throws DukeyException {
        String taskNumberString = ui.getTaskNumber();
        int taskNumber;
        try {
            taskNumber = parseInt(taskNumberString.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeyException("Error! Invalid task number format!");
        }

        if (taskNumber < 0) {
            throw new DukeyException("Error! Invalid task number");
        } else if (taskNumber >= this.list.size()) {
            throw new DukeyException("Error! DukeyList only contains " + this.list.size() + " tasks");
        }

        Task taskToRemove = list.get(taskNumber);
        ui.printDeletedMessage(taskToRemove);
        list.remove(taskNumber);
        this.printSize();
    }

    public void find() {
        String keyword = "";
        try {
            keyword = ui.readKeyword();
        } catch (DukeyException e) {
            ui.printExceptionMessage(e);
        }

        String confirmedKeyword = keyword;
        ArrayList<TaskNumberPair> foundTaskList = new ArrayList<>();
        Iterator<Task> it = getIterator();

        it.forEachRemaining(x -> {
            if (x.getName().contains(confirmedKeyword)) {
                foundTaskList.add(new TaskNumberPair(x, list.indexOf(x)));
            }
        });

        ui.printFoundTaskList(foundTaskList);

    }

    public int getSize() {
        return list.size();
    }

    public void printSize() {
        ui.printSize(getSize());
    }

    public void clearList() {
        this.list.clear();
        System.out.println("DukeyList cleared! DukeyList is now empty.");
    }

    public void save(Storage storage) {
        try {
            storage.save(this);
        } catch (DukeyException e) {
            ui.printExceptionMessage(e);
        }

        ui.printSavedMessage();
    }

    public void initiate(Storage storage) throws FileNotFoundException {
        storage.load(this);

        if (this.list.isEmpty()) {
            ui.printLoadMessage(0);
        } else {
            ui.printLoadMessage(1);
            ui.readList(list);
        }

    }

    public void clearSave(Storage storage) {
        storage.clearFile();
        ui.printClearedMessage();
    }



}
