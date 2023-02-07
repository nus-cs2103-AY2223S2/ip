package duke.tasks;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exceptions.DateTimeFormatException;
import duke.functions.DatabaseWriter;
import duke.functions.Ui;

/**
 * Encapsulates a container for all tasks.
 *
 * @author JamesLiuZX
 *     AY2223-S2 CS2103T
 */
public class TaskList {
    //Variables are kept private and accessed only through internal getter and setter methods.
    private static final int size = 100;
    private ArrayList<Task> records;
    private final String filePath = "./data/duke.txt";

    private int count = 0;
    private Path path = Paths.get(filePath);
    private DatabaseWriter dw = new DatabaseWriter(path);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    private String dateTimeRegex = "^\\d{2}/\\d{2}/\\d{2} \\d{4}$";

    /**
     * Constructor for initialising a new TaskList.
     *
     */
    public TaskList() {
        records = new ArrayList<>(size);
    }

    /**
     * Marks a task in the list as done. (1-indexed)
     *
     * @param index Index of task marked.
     */
    public String mark(int index) {
        index--;
        if (index < 0 || index > size) {
            return String.format("Indices have to be positive and less than %d.", size);
        }
        Task task = this.records.get(index);
        task.markDone();
        dw.setDone(index);
        return Ui.format("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Marks a task in the list as undone. (1-indexed)
     *
     * @param index Index of task marked.
     * @return Completion announcement.
     */
    public String unMark(int index) {
        index--;
        if (index < 0 || index > size) {
            return String.format("Indices have to be positive and less than %d.", size);
        }
        Task task = this.records.get(index);
        task.markUndone();
        dw.setUndone(index);
        return Ui.format("OK, I've marked this task as not done yet:\n\n" + task.toString());
    }

    /**
     * Inserts a generic task to the TaskList.
     *
     * @param name Task name to be added.
     */
    public String insertTask(String name) {
        Task t = new Task(name);
        this.records.add(t);
        dw.addToDb(t);
        this.count += 1;
        return Ui.format("added: " + t);
    }

    /**
     * Inserts a TaskToDo to the TaskList.
     *
     * @param name Task name to be added.
     */
    public String insertToDo(String name) {
        TaskToDo t = new TaskToDo(name);
        this.records.add(t);
        dw.addToDb(t);
        this.count += 1;
        return Ui.format("Got it. I've added this task:\n"
                        + t.toString()
                        + String.format("\nYou now have %s tasks left.", this.count)
        );
    }

    /**
     * Inserts a TaskToDo from the database to the TaskList, does not invoke addToDb() in avoid infinite loop.
     *
     * @param name Task name to be added.
     * @param isInitial Boolean value of whether the program is population the TaskList.
     */
    public void insertToDo(String name, boolean isInitial) {
        TaskToDo t = new TaskToDo(name);
        this.count += 1;
        this.records.add(t);
    }

    /**
     * Inserts a TaskDeadline from the database to the TaskList.
     *
     * @param name Task name to be added.
     * @param time Deadline time in string, according to the dateTimeRegex format.
     * @return Task added successfully announcement.
     */
    public String insertDeadline(String name, String time) throws DateTimeFormatException {
        try {
            if (!time.matches(dateTimeRegex)) { // dd/mm/yy tttt
                throw new DateTimeFormatException();
            }
            LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
            TaskDeadline d = new TaskDeadline(time, dateTime);
            this.records.add(d);
            dw.addToDb(d);
            this.count += 1;
            return Ui.format("Got it. I've added this task:\n"
                    + d.toString()
                    + String.format("\nYou now have %s tasks left.", this.count)
            );
        } catch (DateTimeFormatException e) {
            return e.getMessage();
        }
    }

    /**
     * Inserts a TaskDeadline from the database to the TaskList, does not invoke addToDb() in avoid infinite loop.
     *
     * @param name Task name to be added.
     * @param time Deadline time in string, according to the dateTimeRegex format.
     * @param isInitial Boolean value of whether the program is population the TaskList.
     */
    public void insertDeadline(String name, String time, boolean isInitial) {
        try {
            if (!time.matches(dateTimeRegex)) { // dd/mm/yy tttt
                throw new DateTimeFormatException();
            }
            LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
            TaskDeadline d = new TaskDeadline(time, dateTime);
            this.records.add(d);
            this.count += 1;
        } catch (DateTimeFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserts a TaskEvent from the database to the TaskList.
     *
     * @param name Task name to be added.
     * @param startString Start time in string, according to the dateTimeRegex format.
     * @param endString End time in string, according to the dateTimeRegex format.
     * @return Task added successfully announcement.
     */
    public String insertEvent(String name, String startString, String endString) {
        try {
            if (!startString.matches(dateTimeRegex) // dd/mm/yy tttt
                    | !endString.matches(dateTimeRegex)) {
                throw new DateTimeFormatException();
            }
            LocalDateTime start = LocalDateTime.parse(startString, formatter);
            LocalDateTime end = LocalDateTime.parse(endString, formatter);
            TaskEvent e = new TaskEvent(name, start, end);
            this.records.add(e);
            dw.addToDb(e);
            this.count += 1;
            return Ui.format("Got it. I've added this task:\n"
                    + e.toString()
                    + String.format("\nYou now have %s tasks left.", this.count)
            );
        } catch (DateTimeFormatException e) {
            return e.getMessage();
        }
    }

    /**
     * Inserts a TaskEvent from the database to the TaskList, does not invoke addToDb() in avoid infinite loop.
     *
     * @param name Task name to be added.
     * @param startString Start time in string, according to the dateTimeRegex format.
     * @param endString End time in string, according to the dateTimeRegex format.
     * @param isInitial Boolean value of whether the program is population the TaskList.
     */
    public void insertEvent(String name, String startString, String endString, boolean isInitial) {
        try {
            if (!startString.matches(dateTimeRegex) // dd/mm/yy tttt
                    | !endString.matches(dateTimeRegex)) {
                throw new DateTimeFormatException();
            }
            LocalDateTime start = LocalDateTime.parse(startString, formatter);
            LocalDateTime end = LocalDateTime.parse(endString, formatter);
            TaskEvent e = new TaskEvent(name, start, end);
            this.records.add(e);
            this.count += 1;
        } catch (DateTimeFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes task from TaskList.
     *
     * @param index Index of task to be deleted. (1-indexed)
     */
    public String deleteTask(int index) {
        Task t = this.records.get(index);
        this.records.remove(index);
        dw.removeFromDb(index);
        this.count -= 1;
        return Ui.format("Got it. I've removed this task:\n"
                + t.toString()
                + String.format("\nYou now have %s tasks left.", this.count)
        );
    }


    public ArrayList<Task> findMatchingTasks(String keyword) {
        ArrayList<Task> selected = new ArrayList<>();
        for (Task task: records) {
            if (task.toString().contains(keyword)) {
                selected.add(task);
            }
        }
        return selected;
    }

    /**
     * Returns string representation the TaskList by iterating through it.
     *
     * @return String representation of TaskList.
     */

    @Override
    public String toString() {
        String output = "";
        if (this.records.isEmpty()) {
            return "Please insert a task first.";
        }
        for (int i = 0; i < records.size(); i++) {
            output += String.format("%s. %s\n", i + 1, this.records.get(i));
        }
        return Ui.format(output);
    }
}
