package duke;

public class Ui {
    private final static String INDENT_LINE = "____________________________________________________________";

    private void printFormatted(String toDisplay) {
        System.out.println(INDENT_LINE + "\n" + toDisplay + "\n" + INDENT_LINE);
    }

    private String getTaskList(TaskList taskList) {
        String taskListString = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size() - 1; i++) {
            taskListString += (i + 1) + "." + taskList.get(i) + "\n";
        }
        taskListString += (taskList.size() + "." + taskList.get(taskList.size() - 1));
        return taskListString;
    }

    public void showTaskList(TaskList taskList) {
        printFormatted(getTaskList(taskList));
    }

    public void showInitMessage() {
        printFormatted("Hello! I'm Vincent\n" + "What can I do for you?");
    }

    public void showByeMessage() {
        printFormatted("Bye. Hope to see you again soon!");
    }

    public void showDeleteMessage(Task deletedTask, int len) {
        printFormatted("Noted. I've removed this task:\n" + deletedTask + "\nNow you have " +
                len + " tasks in the list.");
    }

    public void showMarkMessage(Task task, int index) {
        printFormatted("Nice! I've marked this task as done:\n" +
                (index + 1) + "." + task);
    }

    public void showUnmarkMessage(Task task, int index) {
        printFormatted("OK, I've marked this task as not done yet:\n" +
                (index + 1) + "." + task);
    }

    public void showAddMessage(Task task, int len) {
        printFormatted("Got it. I've added this task:\n" + task +
                "\nNow you have " + len + " tasks in the list.");
    }

    public void showLoadingError() {
        printFormatted("The file could not be loaded");
    }
}
