package duke;

public class Ui {
    private final String markAsDone = "Nice! I've marked this task as done:";
    private final String unMarkTask = "OK, I've marked this task as not done yet:";
    private final String addedTask = "Got it, I've added this task:";

    public Ui() {

    }

    public void welcomeMessage() {
        System.out.println("Hello from Bench Monster");
        System.out.println("What can I do for you?");
    }

    public void setMarkAsDone() {
        System.out.println(markAsDone);
    }

    public void setUnMarkTask() {
        System.out.println(unMarkTask);
    }

    public void setAddedTask() {
        System.out.println(addedTask);
    }

}
