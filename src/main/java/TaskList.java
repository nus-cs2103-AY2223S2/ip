import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void list() {
        Ui.separator();
        for (int i=0; i < this.tasks.size(); i++) {
            int currentNumber = i+1;
            Task task = this.tasks.get(i);

            System.out.println("\t" + currentNumber + "." + task);
        }
        Ui.separator();
    }

    public void delete(int index) {
        Ui.separator();
        Task task = this.tasks.get(index - 1);
        this.tasks.remove(index - 1);
        System.out.println("Noted. I've removed this task:" + "\n\t" + task);
        System.out.println("Now you have " + this.tasks.size() +" tasks in the list.");
        Ui.separator();
    }

    public void addToDo(String taskDetails) {
        ToDo task = new ToDo(taskDetails);
        this.tasks.add(task);
        System.out.println("\t" + task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        Ui.separator();
    }

    public void addDeadline(String description, String by ) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(by, format);
        Deadline task = new Deadline(description, date);
        this.tasks.add(task);
        System.out.println("\t" + task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        Ui.separator();
    }

    public void addEvent(String description, String from, String to) {
        Event task = new Event(description, from, to);
        this.tasks.add(task);
        System.out.println("\t" + task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        Ui.separator();
    }

    public void setTaskStatus(int index, boolean isDone) {
        Task task = this.tasks.get(index - 1);
        task.setDone(isDone);
        Ui.separator();
        System.out.println("\tOk, I have marked this task as " + (isDone ? "done" : "not done yet")  +  ":\n\t\t"
                + task);
        Ui.separator();
    }

    public int listSize() {
        return this.tasks.size();
    }


}
