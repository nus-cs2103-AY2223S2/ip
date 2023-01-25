import entities.*;

import java.time.LocalDate;

public class UI {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printDivider();
    }

    public void printDivider() {
        System.out.println("---------------------------------------");
    }

    public void processDelete(TaskList list, int index) {
        Task t = list.getTask(index);
        list.deleteTask(index);
        System.out.println(String.format("ok, this task has been removed:\n %s", t.toString()));
        System.out.println(String.format("Now you have %d tasks in the list", list.getSize()));
        printDivider();
    }

    public void processList(TaskList list) {
        for (int i = 0; i < list.getSize(); i++) {
            Task t = list.getTask(i);
            System.out.println(String.format("%d.%s", i + 1, t.toString()));
        }
        printDivider();
    }

    public void processEvent(TaskList list, String desc, String from, String to) {
        Event event = new Event(desc, from, to);
        list.addTask(event);
        System.out.println(String.format("Sure!, I've added the following events:\n %s", event.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", list.getSize()));
        printDivider();
    }

    public void processDeadline(TaskList list, String desc, String by) {
        Deadline deadline = new Deadline(desc, by);
        list.addTask(deadline);
        System.out.println(String.format("Received, I've added the following deadlines:\n %s", deadline.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", list.getSize()));
        printDivider();
    }

    public void processTodo(TaskList list, String desc) {
        Todo task = new Todo(desc);
        list.addTask(task);
        System.out.println(String.format("alright, I've added the following task:\n %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", list.getSize()));
        printDivider();
    }

    public void processMark(TaskList list, int index) {
        Task task = list.getTask(index);
        task.setDone();
        System.out.println(String.format("Nice, this task has been marked as done:\n %s", task.toString()));
        printDivider();
    }

    public void processUnmark(TaskList list, int index) {
        Task task = list.getTask(index);
        task.setUndone();
        System.out.println(String.format("Nice, this task has been marked as done:\n %s", task.toString()));
        printDivider();
    }

    public void processPrintDate(TaskList list, String date) {
        LocalDate now = LocalDate.parse(date.trim());
        System.out.println("Tasks occurring on " + now.toString() + ":");
        for (int i = 0; i < list.getSize(); i++) {
            Task currTask = list.getTask(i);
            if (currTask instanceof Deadline) {
                if (now.equals(((Deadline) currTask).getBy())) {
                    System.out.println(currTask.toString());
                }
            } else if (currTask instanceof Event) {
                if (now.equals(((Event) currTask).getTo()) || now.equals(((Event) currTask).getFrom())) {
                    System.out.println(currTask.toString());
                }
            }
        }
        printDivider();
    }

}
