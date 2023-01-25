package duke;

public class Ui {
    static final String BORDER = "----------------------------------------";

    protected void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?\n" + BORDER);
    }

    protected void showLine() {
        System.out.println(BORDER);
    }

    protected void showError(DukeException e) {
        System.out.println(e.getMessage() + "\n" + BORDER);
    }

    protected void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n" + BORDER);
    }

    protected void markDoneMessage(Task t) {
        System.out.println("Nice! I've marked this task as done:\n" +
                t.fullMessage() + "\n" + BORDER);
    }

    protected void markUndoneMessage(Task t) {
        System.out.println("OK, I've marked this task as not done yet:\n" +
                t.fullMessage() + "\n" + BORDER);
    }

    protected void sizeMessage(TaskList taskList) {
        System.out.println("Now you have " + taskList.getSize() + " tasks in this list\n" + BORDER);
    }

    protected void addMessage(Task t, TaskList taskList) {
        System.out.println("Got it. I've added this task:\n" + t.fullMessage());
        sizeMessage(taskList);
    }

    protected void deleteMessage(Task t, TaskList taskList) {
        System.out.println("Noted. I've removed this task:\n" + t.fullMessage());
        sizeMessage(taskList);
    }
}
