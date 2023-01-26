/**
 * Storage for user inputs up to 100 records.
 *
 * @author JamesLiuZX
 * AY2223-S2 CS2103T
 */
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    //Variables are kept protected and accessed only through internal getter and setter methods.
    protected static final int size = 100;
    protected ArrayList<Task> records;
    private final String filePath = "./data/duke.txt";
    private final String database = "duke.txt";
    private Path path = Paths.get(filePath);
    private DatabaseWriter dw = new DatabaseWriter(path);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");

    //Constructor
    public TaskList() {
        records = new ArrayList<>(size);
    }

    public void mark(int index) {
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

    public void unMark(int index) {
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

    public void insert(String record) {
        Task t = new Task(record);
        this.records.add(t);
        dw.addToDb(t);
        System.out.println(Ui.format("added: " + t));
    }

    public void insertToDo(String name) {
        TaskToDo t = new TaskToDo(name);
        this.records.add(t);
        dw.addToDb(t);
        System.out.println(Ui.format("Got it. I've added this task:\n" + t.toString()));
    }

    public void insertToDo(String name, boolean isInitial) {
        TaskToDo t = new TaskToDo(name);
        this.records.add(t);
    }

    public void insertDeadline(String name, String time) {
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        TaskDeadline d = new TaskDeadline(time, dateTime);
        this.records.add(d);
        dw.addToDb(d);
        System.out.println(Ui.format("Got it. I've added this task:\n" + d.toString()));
    }
    public void insertDeadline(String name, String time, boolean isInitial) {
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        TaskDeadline d = new TaskDeadline(time, dateTime);
        this.records.add(d);
    }

    public void insertEvent(String name, String time) {
        String[] times = time.split("/", 2);
        LocalDateTime start = LocalDateTime.parse(times[0], formatter);
        LocalDateTime end = LocalDateTime.parse(times[1], formatter);
        TaskEvent e = new TaskEvent(name, start, end);
        this.records.add(e);
        dw.addToDb(e);
        System.out.println(Ui.format("Got it. I've added this task:\n" + e.toString()));
    }
    public void insertEvent(String name, String time, boolean isInitial) {
        String[] times = time.split("/", 2);
        LocalDateTime start = LocalDateTime.parse(times[0], formatter);
        LocalDateTime end = LocalDateTime.parse(times[1], formatter);
        TaskEvent e = new TaskEvent(name, start, end);
        this.records.add(e);
    }

    public Task getTask(int index) {
        return this.records.get(index);
    }

    public void deleteTask(int index) {
        Task t = this.records.get(index);
        this.records.remove(index);
        dw.removeFromDb(index);
        System.out.println(Ui.format("Noted. I've removed this task:\n" + t.toString()));
    }

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
