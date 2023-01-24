import Tasks.Task;

public class Ui {
    public Ui() {
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showLine() {
        System.out.println("_______________________");
    }

    public String readCommand() {

    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showAdded(TaskList l) {
        System.out.println("Got it. I've added this task:\n" + l.get(l.size()-1)
                + "\nNow you have " + l.size() + " tasks in the list.");
    }

    public void showDelete(Task t, TaskList l) {
        System.out.println("Noted. I've removed this task:\n" + t +
                "\nNow you have " + l.size() + " tasks in the list.")
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showList(TaskList l) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0 ; i < l.size() ; i++) {
            System.out.println((i+1) + ". " + l.get(i));
        }
    }

}
