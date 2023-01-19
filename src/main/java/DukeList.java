/**
 * Storage for user inputs up to 100 records.
 *
 * @author JamesLiuZX
 * AY2223-S2 CS2103T
 */
import java.util.ArrayList;

public class DukeList {
  //Variables are kept protected and accessed only through internal getter and setter methods.
  protected static final int size = 100;
  protected static ArrayList<Task> records;

  //Constructor
  public DukeList() {
    records = new ArrayList<>(size);
  }

  public static void insert(String record) {
      Task t = new Task(record);
      records.add(t);
      System.out.println(Duke.format("added: " + t));
  }

  public void mark(int index) {
    if (index < 0 || index > size) {
      System.out.println(String.format("Indices have to be positive and less than %d.", size));
      return;
    }
    Task task = records.get(index);
    task.markDone();
    System.out.println(Duke.format("Nice! I've marked this task as done:\n" + task.toString()));
    return;
  }

  public void unMark(int index) {
    if (index < 0 || index > size) {
      System.out.println(String.format("Indices have to be positive and less than %d.", size));
      return;
    }
    Task task = records.get(index);
    task.markUndone();
    System.out.println(Duke.format("OK, I've marked this task as not done yet:\n\n" + task.toString()));
    return;
  }

  public static void insertToDo(String name) {
    TaskToDo t = new TaskToDo(name);
    records.add(t);
    System.out.println(Duke.format("Got it. I've added this task:\n" + t.toString()));
  }

  public static void insertDeadline(String name, String time) {
    TaskDeadline d = new TaskDeadline(name, time);
    records.add(d);
    System.out.println(Duke.format("Got it. I've added this task:\n" + d.toString()));
  }

  public static void insertEvent(String name, String time) {
    String[] times = time.split("/", 2);
    String from = times[0];
    String to = times[1];
    TaskEvent e = new TaskEvent(name, from, to);
    records.add(e);
    System.out.println(Duke.format("Got it. I've added this task:\n" + e.toString()));
  }

  public Task getTask(int index) {
    return records.get(index);
  }

  public void deleteTask(int index) {
    Task t = records.get(index);
    records.remove(index);
    System.out.println(Duke.format("Noted. I've removed this task:\n" + t.toString()));
  }

  @Override
  public String toString() {
    String output = "";
    if (records.isEmpty()) {
      return "Please insert a task first.";
    }
    for (int i = 0; i < records.size(); i++) {
      output += String.format("%s. %s\n", i+1, records.get(i));
    }
    return Duke.format(output);
  }
}
