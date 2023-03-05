package duke;
/** Handles the interface to interact with the user */
public class Ui {
    private TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /** Formats reply to tell user task has been added. */
    public String formatAddTaskReply(Task task) {
        String formattedReply;
        assert taskList != null;
        assert task != null;
        formattedReply = String.format(
                "Got it. I've added this task:\n\t%s\n"
                        + "Now you have %d task(s) in the list.",
                task,
                taskList.getListSize());
        return formattedReply;
    }

    public String formatDelTaskReply(Task task) {
        String formattedReply;
        formattedReply = String.format("I've deleted the task:\n%s",
                task.toString());
        return formattedReply;
    }

    public String formatlistTasksReply() {
        return String.format(
                "Here are the tasks in your list:\n%s", taskList.getTaskList());
    }

    public String formatMarkTaskReply(Task task) {
        return String.format(
                "Nice! I've marked this task as done:\n%s", task.toString());
    }

    public String formatUnmarkTaskReply(Task task) {
        return String.format(
                "OK, I've marked this task as not done yet:\n%s", task.toString());
    }
}
