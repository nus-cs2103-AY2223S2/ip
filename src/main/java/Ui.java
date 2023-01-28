public class Ui {
    public Ui() {};

    public void welcomeMessage() {
        System.out.println("Hello!\n" + "What can I do for you?\n");
    }
    public void showLine() {
        System.out.println("----------------------------------");
    }

    public void listMessage() {
        System.out.println("Here are the tasks in your list: \n");
    }

    public void addMessage(Task newTask, TaskList list) {
        System.out.println("Got it. I've added this task:\n" + newTask + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public void markMessage(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" + task);
    }

    public void unmarkMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n" + task);
    }

    public void deleteMessage(Task removed, TaskList list) {
        System.out.println("Noted. I've removed this task: \n" + removed + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}