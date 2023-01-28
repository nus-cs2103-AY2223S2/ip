package duke;
import java.util.Scanner;
import duke.task.Task;
public class Ui {
    public Ui() {

    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Meow I'm Toto! What can I for you?");
    }

    public void sayGoodbye() {
        System.out.println("Byebye CATch you later!");
    }

    public void showMarked(Task task) {
        System.out.println("I've marked this task as done: " + task);
    }

    public void showUnmarked(Task task) {
        System.out.println("I've marked this task as not done yet: " + task);
    }

    public void showDeleted(Task task) {
        System.out.println("I've deleted this task: " + task);
    }

    public void showAddTask(Task task) {
        System.out.println("Meow! Just added: \n" + task);
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void askBy() {
        System.out.println("By?");
    }

    public void askFrom() {
        System.out.println("From?");
    }

    public void askTo() {
        System.out.println("To?");
    }
}
