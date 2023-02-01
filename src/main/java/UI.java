import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UI {
    public void start() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showList(TasksList list) {
        System.out.println("Here are the task(s) in your list:");
        for (int i = 1; i <= list.getSize(); i++) {
            System.out.println(i + "." + list.getTask(i-1).toString());
        }
    }

    public void mark(TasksList list, int index) {
        list.getTask(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n  " + list.getTask(index - 1).toString());
    }

    public void unmark(TasksList list, int index) {
        list.getTask(index - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n  " + list.getTask(index - 1).toString());
    }
    public void addTodo(TasksList list, String name) {
        list.addTask(new Todo(name));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + list.getTask(list.getSize() - 1).toString());
        System.out.println("Now you have " + list.getSize() + " task(s) in the list.");
    }

    public void addDeadline(TasksList list, String name, String deadline) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime formattedDeadline = LocalDateTime.parse(deadline, dateTimeFormatter);
        list.addTask(new Deadline(name, formattedDeadline));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + list.getTask(list.getSize() - 1).toString());
        System.out.println("Now you have " + list.getSize() + " task(s) in the list.");
    }

    public void addEvent(TasksList list, String name, String start, String end) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime formattedstartTime = LocalDateTime.parse(start, dateTimeFormatter);
        LocalDateTime formattedendTime= LocalDateTime.parse(end, dateTimeFormatter);
        list.addTask(new Event(name, formattedstartTime, formattedendTime));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + list.getTask(list.getSize() - 1).toString());
        System.out.println("Now you have " + list.getSize() + " task(s) in the list.");
    }

    public void removeTask(TasksList list, int index) {
        System.out.println("Noted. I've removed this task :");
        System.out.println("  " + list.removeTask(index - 1).toString());
        System.out.println("Now you have " + list.getSize() + " task(s) in the list.");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
