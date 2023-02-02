package duke.tasks;

import duke.exceptions.DateTimeFormatException;
import duke.functions.DatabaseWriter;
import duke.functions.Ui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Encapsulates a container for all tasks.
 *
 * @author JamesLiuZX
 * AY2223-S2 CS2103T
 */
public class TaskList {
    //Variables are kept private and accessed only through internal getter and setter methods.
    private static final int size = 100;
    private ArrayList<Task> records;
    private final String filePath = "./data/duke.txt";
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
    public void mark(int index) {
        index--;
        if (index < 0 || index > size) {
            System.out.println(String.format("Indices have to be positive and less than %d.", size));
            return;
        }
        Task task = this.records.get(index);
        task.markDone();
        dw.setDone(index);
        System.out.println(Ui.format("Nice! I've marked this task as done:\n" + task.toString()));
        return;
    }

    /**
     * Marks a task in the list as undone. (1-indexed)
     *
     * @param index Index of task marked.
     */
    public void unMark(int index) {
        index--;
        if (index < 0 || index > size) {
            System.out.println(String.format("Indices have to be positive and less than %d.", size));
            return;
        }
        Task task = this.records.get(index);
        task.markUndone();
        dw.setUndone(index);
        System.out.println(Ui.format("OK, I've marked this task as not done yet:\n\n" + task.toString()));
        return;
    }

    /**
     * Inserts a generic task to the TaskList.
     *
     * @param name Task name to be added.
     */
    public void insertTask(String name) {
        Task t = new Task(name);
        this.records.add(t);
        dw.addToDb(t);
        System.out.println(Ui.format("added: " + t));
    }

    /**
     * Inserts a TaskToDo to the TaskList.
     *
     * @param name Task name to be added.
     */
    public void insertToDo(String name) {
        TaskToDo t = new TaskToDo(name);
        this.records.add(t);
        dw.addToDb(t);
        System.out.println(Ui.format("Got it. I've added this task:\n" + t.toString()));
    }

    /**
     * Inserts a TaskToDo from the database to the TaskList, does not invoke addToDb() in avoid infinite loop.
     *
     * @param name Task name to be added.
     * @param isInitial Boolean value of whether the program is population the TaskList.
     */
    public void insertToDo(String name, boolean isInitial) {
        TaskToDo t = new TaskToDo(name);
        this.records.add(t);
    }

    /**
     * Inserts a TaskDeadline from the database to the TaskList.
     *
     * @param name Task name to be added.
     * @param time Deadline time in string, according to the dateTimeRegex format.
     */
    public void insertDeadline(String name, String time) throws DateTimeFormatException {
        System.out.println(time);
        try {
            if (!time.matches(dateTimeRegex)) { // dd/mm/yy tttt
                throw new DateTimeFormatException();
            }
            LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
            TaskDeadline d = new TaskDeadline(time, dateTime);
            this.records.add(d);
            dw.addToDb(d);
            System.out.println(Ui.format("Got it. I've added this task:\n" + d.toString()));
        } catch (DateTimeFormatException e) {
            System.out.println(e.getMessage());
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
            System.out.println(Ui.format("Got it. I've added this task:\n" + d.toString()));
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
     */
    public void insertEvent(String name, String startString, String endString) {
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
            System.out.println(Ui.format("Got it. I've added this task:\n" + e.toString()));
        } catch (DateTimeFormatException e) {
            System.out.println(e.getMessage());
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
            System.out.println(Ui.format("Got it. I've added this task:\n" + e.toString()));
        } catch (DateTimeFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes task from TaskList.
     *
     * @param index Index of task to be deleted. (1-indexed)
     */
    public void deleteTask(int index) {
        Task t = this.records.get(index);
        this.records.remove(index);
        dw.removeFromDb(index);
        System.out.println(Ui.format("Noted. I've removed this task:\n" + t.toString()));
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
            output += String.format("%s. %s\n", i+1, this.records.get(i));
        }
        return Ui.format(output);
    }
}
