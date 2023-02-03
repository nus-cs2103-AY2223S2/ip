package Ui;
import java.time.format.DateTimeFormatter;
import TaskList.TaskList;
import Task.Task;

public class Ui {
    final static String ENTRY_OUTPUT = "Hello! I'm Duke\nWhat can I do for you?";
    final static String BYE_OUTPUT = "Bye. Hope to see you again soon!";
    final static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/d HHmm");

    final static DateTimeFormatter outputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public boolean isClosed;
    public Ui() {
       this.isClosed = false;
    }

    public void printException(Exception e) {
        System.out.println(e);
    }

    public void printBye() {
        System.out.println(BYE_OUTPUT);
    }

    public void printEntry() {
        System.out.println(ENTRY_OUTPUT);
    }

    public void printHandleDelete(Task task, TaskList list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
    }

    public void printHandleMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    public void printHandleUnmark(Task task) {
        System.out.println("OK,, I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
    }

    public void printAddTask(Task newTask, TaskList list) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
    }

    public void printGetList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.getTask(i);
            System.out.println(i+1 + "." + task.toString());
        }
    }

    public void showLoadingError() {
        System.out.println("Could not load list. Using a new, empty list instead");
    }


}
