package crystal;

import crystal.task.Task;
import crystal.task.Todo;
import crystal.task.Deadline;
import crystal.task.Event;
import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);
    public String readCommand() {
        return sc.nextLine();
    }
    public void showWelcome() {
        System.out.println(" ____________________________________________________________");
        System.out.println(" Hi! I am CRYSTAL.\n How may I be of assistance?");
        System.out.println(" ____________________________________________________________");
    }

    public void showError(CrystalException e) {
        System.out.println(" ____________________________________________________________");
        System.out.println(e.getMessage());
        System.out.println(" ____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Load Error");
    }

    public void printList(TaskList task) {
        System.out.println(" ____________________________________________________________");
        System.out.println("Here are your current tasks:");
        for (int i = 0; i < task.size(); i++) {
            System.out.println(i + 1 + ". " + task.get(i));
        }
        System.out.println(" ____________________________________________________________");
    }

    public void printUnmark(TaskList task, int num) {
        Task t = task.get(num-1);
        System.out.println(" ____________________________________________________________");
        System.out.println("Alright, I've marked this task as not done: ");
        t.isDone = false;
        System.out.println(t.toString());
        System.out.println(" ____________________________________________________________");
    }

    public void printMark(TaskList task, int num) {
        Task t = task.get(num - 1);
        System.out.println(" ____________________________________________________________");
        System.out.println("Alright, I've marked the task as done: ");
        t.isDone = true;
        System.out.println(t.toString());
        System.out.println(" ____________________________________________________________");
    }

    public void printTodo(TaskList task, Todo t) {
        System.out.println(" ____________________________________________________________");
        System.out.println("Alright, I've added this task: ");
        System.out.println(t.toString());
        System.out.println("Current number of tasks : " + task.size());
        System.out.println(" ____________________________________________________________");
    }

    public void printDeadline(TaskList task, Deadline d) {
        System.out.println(" ____________________________________________________________");
        System.out.println("Alright, I've added this task: ");
        System.out.println(d.toString());
        System.out.println("Current number of tasks : " + task.size());
        System.out.println(" ____________________________________________________________");
    }

    public void printEvent(TaskList task, Event e) {
        System.out.println(" ____________________________________________________________");
        System.out.println("Alright, I've added this task: ");
        System.out.println(e.toString());
        System.out.println("Current number of tasks: " + task.size());
        System.out.println(" ____________________________________________________________");
    }

    public void printBye() {
        System.out.println(" ____________________________________________________________");
        System.out.println(" Thank You. Please come by again~!");
        System.out.println(" ____________________________________________________________");
    }

    public void printDelete(TaskList task, int num) {
        System.out.println(" ____________________________________________________________");
        System.out.println("Alright, I've removed this task: ");
        Task item = task.get(num - 1);
        task.remove(num - 1);
        System.out.println(item.toString());
        System.out.println("Current number of tasks: " + task.size());
        System.out.println(" ____________________________________________________________");
    }
}
